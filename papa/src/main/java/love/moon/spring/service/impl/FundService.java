package love.moon.spring.service.impl;

import love.moon.spring.dao.FundDao;
import love.moon.spring.dao.FundHistoryDao;
import love.moon.spring.model.Cart;
import love.moon.spring.model.Fund;
import love.moon.spring.model.FundDTO;
import love.moon.spring.model.FundHistory;
import love.moon.spring.service.IFundService;
import love.moon.util.DateUtil;
import love.moon.util.RandomUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author: lovemooner
 * Date: 2018/9/2 13:42
 */
@Component
public class FundService implements IFundService {

    @Autowired
    private FundDao fundDao;
    @Autowired
    private FundHistoryDao fundHistoryDao;


    class FundDataIniter implements Runnable {

        private int name;

        public FundDataIniter(int name) {
            this.name = name;
        }



        @Override
        public void run() {
            System.out.println("initer name="+name+"begin");
            long start = System.currentTimeMillis();
            Fund fund = new Fund();
            RandomUtil.random();
            fund.setName(RandomUtil.randomChars(10));
            fund.setCode(RandomUtil.randomChars(10));
            fund.setAttr1(RandomUtil.randomChars(10));
            fund.setAttr2(RandomUtil.randomChars(10));

             Session session=fundDao.getSessionFactory().openSession();

            try {
               session.beginTransaction();
               session.save(fund);
               for (int j = 0; j < 3000; j++) {
                   FundHistory fundHistory = new FundHistory();
                   fundHistory.setFundId(fund.getId());
                   fundHistory.setName(RandomUtil.randomChars(10));
                   fundHistory.setScore(RandomUtil.randomInt(100));
                   fundHistory.setFetchDate(DateUtil.getInnerDayTime(j * -1));
                   fundHistory.setStartDate(fundHistory.getFetchDate());
                   fundHistory.setEndDate(fundHistory.getFetchDate());
                   fundHistory.setAttr1(RandomUtil.randomChars(10));
                   fundHistory.setAttr2(RandomUtil.randomChars(10));
                   fundHistory.setAttr3(RandomUtil.randomChars(10));
                   fundHistory.setAttr4(RandomUtil.randomChars(10));
                   fundHistory.setAttr5(RandomUtil.randomChars(10));
                   fundHistory.setAttr6(RandomUtil.randomChars(10));
                   session.save(fundHistory);
                   if(j%100==0){
                       session.flush();
                       System.out.println("session flush,"+DateUtil.convertDateLongToString(System.currentTimeMillis(),DateUtil.ALL));
                   }
               }
               session.getTransaction().commit();
               System.out.println("commit on fund Data,i_name=" + name + ",times:" + (System.currentTimeMillis() - start) + "ms");
           }finally {
                session.close();
            }
        }
    }


    public void initData() {
        ExecutorService executor = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 100; i++) {
            System.out.println("start round i="+i);
            long start=System.currentTimeMillis();
            for(int j=0;j<20;j++){
                executor.execute(new FundDataIniter(i));
            }
            int activeCount = ((ThreadPoolExecutor) executor).getActiveCount();
            while (activeCount > 0) {
                activeCount = ((ThreadPoolExecutor) executor).getActiveCount();
                int queueSize = ((ThreadPoolExecutor) executor).getQueue().size();
                long completedTaskCount = ((ThreadPoolExecutor) executor).getCompletedTaskCount();
                System.out.println("排队线程数:" + queueSize
                        + " 活动线程数:" + activeCount
                        + " 执行完成线程数:" + completedTaskCount
                        +",round ="+i
                        +" times="+(System.currentTimeMillis()-start)/1000 +"s"
                );
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Thread "+Thread.currentThread().getName()+",init Date Done");

    }


    public FundDTO mockHand() {
        int count=1;
        int start = 0;
        int limit = 10;
        FundDTO fundDTO = new FundDTO();
        Session session = fundDao.getSessionFactory().openSession();
        Query query = session.createQuery("from Fund").setFirstResult(start).setMaxResults(limit);
        Map map = new HashMap();
        List<Fund> funds = query.list();
        fundDTO.setFunds(funds);
        for (Fund fund : funds) {
            Long l_begin=System.currentTimeMillis();
            Long startDate = DateUtil.getInnerDayTime(-180);
            Long endDate = System.currentTimeMillis();
//            System.out.println("startDate=" + DateUtil.convertDateLongToString(startDate, DateUtil.ALL) + ",endDate=" + DateUtil.convertDateLongToString(endDate, DateUtil.ALL));
            Query query1 = session.createQuery("from FundHistory where fundId=:fundId and fetchDate> :startDate and fetchDate< :endDate")
                    .setParameter("fundId", fund.getId())
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate);
            List<FundHistory> fundHistories = query1.list();
            fundDTO.setFundHistories(fundHistories);
            System.out.println("round:"+(count++)+",times:"+(System.currentTimeMillis()-l_begin)/1000+"s,fundHistories size"+fundHistories.size());
        }
        return fundDTO;
    }

}
