package lock;

/**
 * @author CTX
 * @since 2019/11/14 21:03
 * Java线程：锁
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        //创建并发访问的账户
        MyCount myCount = new MyCount("1234567890", 10000);
        //创建一个锁对象  不公平锁
        Lock lock = new ReentrantLock();
        //创建一个线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        //创建一些并发访问用户，一个信用卡，存的存，取的取，好热闹啊
        User u1 = new User("张三", myCount, -4000, lock);
        User u2 = new User("张三他爹", myCount, 6000, lock);
        User u3 = new User("张三他弟", myCount, -8000, lock);
        User u4 = new User("张三", myCount, 800, lock);
        //在线程池中执行各个用户的操作
        pool.execute(u1);
        pool.execute(u2);
        pool.execute(u3);
        pool.execute(u4);
        //关闭线程池
        pool.shutdown();
    }
}