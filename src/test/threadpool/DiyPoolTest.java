package test.threadpool;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author CTX
 * @since 2019/10/30 1:24
 * 使用了有界队列，自定义ThreadFactory和拒绝策略的demo
 */
public class DiyPoolTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        /**
         *自定义线程池 参数：
         * corePoolSize -池中所保存的线程数，包括空闲线程。
         * maximumPoolSize -池中允许的最大线程数。
         * keepAliveTime -当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
         * unit - keepAliveTime参数的时间单位。
         * workQueue -执行前用于保持任务的队列。此队列仅保持由execute方法提交的Runnable任务。
         *
         * 抛出：
         * IllegalArgumentException -如果 corePoolSize或 keepAliveTime小于零，或者 maximumPoolSize小于或等于零，或者 corePoolSize大于 maximumPoolSize。
         * NullPointerException -如果workQueue为 null
         */
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadFactory threadFactory = new NameTreadFactory();
        RejectedExecutionHandler handler = new MyIgnorePolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程

        for (int i = 1; i <= 10; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            executor.execute(task);
        }

        System.in.read(); //阻塞主线程
    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println( r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

    static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString() + " is running!");
                Thread.sleep(3000); //让任务执行慢点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }
    }
}
