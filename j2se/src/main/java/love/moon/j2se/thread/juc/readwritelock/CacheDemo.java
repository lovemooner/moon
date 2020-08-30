package love.moon.j2se.thread.juc.readwritelock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author dongnan
 * @date 2020/8/11 11:26
 */
public class CacheDemo {
    /**
     * 缓存器
     */
    private Map<String, Object> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public Object get(String id){
        Object value = null;
        lock.readLock().lock();//首先开启读锁，从缓存中去取
        try{
            value = map.get(id);
            //如果缓存中没有释放读锁，上写锁
            if(value == null){
                lock.readLock().unlock();
                lock.writeLock().lock();
                try{
                    if(value == null){
                        //此时可以去数据库中查找，这里简单的模拟一下
                        value = "aaa";
                    }
                }finally{
                    lock.writeLock().unlock(); //释放写锁
                }
                lock.readLock().lock(); //然后再上读锁
            }
        }finally{
            lock.readLock().unlock(); //最后释放读锁
        }
        return value;
    }

    public static void main(String[] args) {
        CacheDemo cacheDemo=new CacheDemo();
        Object value= cacheDemo.get("0");
        System.out.println(value);
    }

}
