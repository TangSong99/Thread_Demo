package threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author CTX
 * @since 2019/10/30 1:07
 */
public class DiyPool {
    public static void main(String[] args) {
        //创建等待队列
        BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);

        //创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,3,2, TimeUnit.MILLISECONDS,bqueue);

        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        Thread t1 = new MyThreads();
        Thread t2 = new MyThreads();
        Thread t3 = new MyThreads();
        Thread t4 = new MyThreads();
        Thread t5 = new MyThreads();
        Thread t6 = new MyThreads();
        Thread t7 = new MyThreads();

        //将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        pool.execute(t7);
        //关闭线程池
        pool.shutdown();
    }
}

class MyThreads extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行。。。");
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}