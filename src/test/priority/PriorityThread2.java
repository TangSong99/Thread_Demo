package test.priority;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author CTX
 * @since 2019/10/28 21:38
 */
public class PriorityThread2 {
    public static void main(String[] args)  {
        /**
         *功能描述
         * java5以后提供的一个多线程操作方法，创建一个只有一个线程的线程池操作，
         * 会创建一个线程队列，按FIFO的顺序执行里面的线程。
         * @author CTX
         * @date 2019/10/28
         */
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Thread1());
        executor.submit(new Thread2());
        executor.submit(new Thread3());
        executor.shutdown();
    }

    public static class Thread1 implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread1");
        }
    }

    public static class Thread2 implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread2");
        }
    }

    public static class Thread3 implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread3");
        }
    }
}
