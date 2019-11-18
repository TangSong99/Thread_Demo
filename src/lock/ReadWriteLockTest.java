package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author CTX
 * @since 2019/11/15 10:12
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        //创建并发访问的账户
        MyCount myCount = new MyCount("123456789", 10000);
        //创建一个锁对象  读写锁  sync = fair ? new FairSync() : new NonfairSync();
        ReadWriteLock lock = new ReentrantReadWriteLock(false);
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        //创建一些并发访问用户，一个信用卡，存的存，取的取，好热闹啊
        User2 u1 = new User2("张三", myCount, -4000, lock, false);
        User2 u2 = new User2("张三他爹", myCount, 6000, lock, false);
        User2 u3 = new User2("张三他弟", myCount, -8000, lock, false);
        User2 u4 = new User2("张三", myCount, 800, lock,false);
        User2 u5 = new User2("张三他爹", myCount, 0, lock,true);
        //在线程池中执行各个用户的操作
        pool.execute(u1);
        pool.execute(u2);
        pool.execute(u3);
        pool.execute(u4);
        pool.execute(u5);
        //关闭线程池
        pool.shutdown();
    }
}