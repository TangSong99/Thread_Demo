package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author CTX
 * @since 2019/10/30 0:27
 */
public class ScheduledThreadPool {
    public static void main(String[] args) {
        //创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。
        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();

        //创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
        //ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);

        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();
        //将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        //使用延迟执行风格的方法  延迟5秒
        pool.schedule(t4, 3000, TimeUnit.MILLISECONDS);
        pool.schedule(t5, 3000, TimeUnit.MILLISECONDS);
        //关闭线程池
        pool.shutdown();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行。。。");
    }
}