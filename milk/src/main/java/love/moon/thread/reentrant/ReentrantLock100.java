package love.moon.thread.reentrant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2017/10/18 15:06
 */
public class ReentrantLock100 {
    private Logger LOG = LoggerFactory.getLogger(ReentrantLock100.class);

    private Lock lock = new ReentrantLock();

    public void testSleepState() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread.sleep(1000l);
        System.out.println("sleep state:" + t1.getState());
    }

    public void testWaitState() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitMethod();
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread.sleep(500l);
        System.out.println("============ WaitState=========");
        System.out.println("wait state:" + t1.getState());
        t1.interrupt();
        Thread.sleep(1000l);
        System.out.println("After interrupt, State->" + "t1:" + t1.getState());
    }

    public synchronized void waitMethod() throws InterruptedException {
        this.wait();
    }

    public synchronized void lockWithSynchronized() {
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void lockWithReentrantLock1() {
        try {
            lock.lock();
            LOG.info("lockWithReentrantLock1 currentThread:" + Thread.currentThread().getName());
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void lockWithTryLock() {
        try {
            lock.tryLock(2000l, TimeUnit.SECONDS);
            LOG.info("lockWithReentrantLock1 currentThread:" + Thread.currentThread().getName());
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void lockWithLockInterruptibly() throws InterruptedException {
        try {
            lock.lockInterruptibly();
            LOG.info("lockWithReentrantLock2 currentThread:" + Thread.currentThread().getName());
            Thread.sleep(5000l);
        } finally {
            lock.unlock();
        }
    }

    public void testSynchronized() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockWithSynchronized();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockWithSynchronized();
            }
        });
        t1.start();
        t2.start();
        Thread.sleep(1000l);
        System.out.println("synchronized State->" + "t1:" + t1.getState() + ",t2:" + t2.getState());
        t2.interrupt();
        Thread.sleep(3000l);
        System.out.println("after interrupt, State->" + "t1:" + t1.getState() + ",t2:" + t2.getState() + ",t2 isInterrupted:" + t2.isInterrupted());
    }

    public void testTryLock() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockWithTryLock();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockWithTryLock();
            }
        });
        t1.start();
        t2.start();
        LOG.info("TryLock============");
        while (!Thread.State.TERMINATED.equals(t1.getState())) {
            Thread.sleep(1000l);
            LOG.info("State->" + "t1:" + t1.getState() + ",t2:" + t2.getState());
        }
        t2.interrupt();
        Thread.sleep(3000l);
        System.out.println("After interrupt-->" + "t1:" + t1.getState() + ",t2:" + t2.getState() + ",t2 isInterrupted:" + t2.isInterrupted());
    }

    public void testReentrantLock() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockWithReentrantLock1();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockWithReentrantLock1();
            }
        });
        t1.start();
        t2.start();
        t1.setName("T1");
        t2.setName("T2");
        Thread.sleep(1000l);
        LOG.info("ReentrantLock======");
        LOG.info("State->" + "t1:" + t1.getState() + ",t2:" + t2.getState());
        t2.interrupt();
        Thread.sleep(1000l);
        LOG.info("After interrupt for lock,t2 is still  WAITING,State->" + "t1:" + t1.getState() + ",t2:" + t2.getState());
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lockWithLockInterruptibly();
                } catch (InterruptedException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lockWithLockInterruptibly();
                } catch (Exception e) {
                    LOG.error(e.getMessage());
                }
            }
        });
        t3.setName("T3");
        t4.setName("T4");
        t3.start();
        t4.start();
        Thread.sleep(1000l);
        LOG.info("State->" + "t4:" + t4.getState());
        t4.interrupt();
        Thread.sleep(1000l);
        LOG.info("After interrupt for lockInterruptibly,t4 is TERMINATED, State->" + "t4:" + t4.getState());
//        while (true) {
//            System.out.println("In while ,t4 State->" + t4.getState());
//        }

    }

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock100 demo = new ReentrantLock100();
//        Thread.State state = Thread.currentThread().getState();
//        System.out.println("currentThread state :" + state);
//        demo.testWaitState();
//        demo.testSleepState();
//        demo.testSynchronized();
//        demo.testReentrantLock();
        demo.testTryLock();
    }

}
