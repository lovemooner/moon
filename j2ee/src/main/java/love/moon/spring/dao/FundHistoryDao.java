package love.moon.spring.dao;

import love.moon.spring.model.Cart;
import love.moon.spring.model.Fund;
import love.moon.spring.model.FundHistory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: lovemooner
 * Date: 2018/9/2 13:45
 */
@Repository
public class FundHistoryDao extends BaseDao<FundHistory> {

    public FundHistoryDao() {
        super(Fund.class);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<FundHistory> getFundHistory(Long fundId,Long startDate,Long endDate) {
        Query query = sessionFactory.getCurrentSession().createQuery("from FundHistory where fetchDate> :startDate and fetchDate< :endDate")
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                ;
        return query.list();
    }
}
