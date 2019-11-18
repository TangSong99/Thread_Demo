package threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author CTX
 * @since 2019/10/26 19:59
 */
public class RunnablePool {
    public static void main(String[] args) {
        //创建线程池对象  参数2，代表有2个线程的线程池
        ExecutorService service = Executors.newFixedThreadPool(2);

//        创建一个使用单个 worker线程的 Executor，以无界队列方式来运行该线程。
//        ExecutorService service = Executors.newSingleThreadExecutor();

//        创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
//        ExecutorService service = Executors.newCachedThreadPool();

        //创建Runnable线程任务对象
        TaskRunnable task = new TaskRunnable();
        //从线程池中获取线程对象
        service.submit(task);
        System.out.println("----------------------");
        //再获取一个线程对象
        service.submit(task);
        //线程池只有两个，等待第一个执行完后执行第三个
        service.submit(task);

        /**
         * 与service.execute(task)区别
         *
         * 1.execute只能接受Runnable类型的任务
         *   submit不管是Runnable还是Callable类型的任务都可以接受，但是Runnable返回值均为void，所以使用Future的get()获得的还是null
         * 2.execute没有返回值
         *   submit有返回值，所以需要返回值的时候必须使用submit
         * 3.execute中抛出异常:execute中的是Runnable接口的实现，所以只能使用try、catch来捕获CheckedException，通过实现UncaughtExceptionHande接口处理UncheckedException
         *   即和普通线程的处理方式完全一致
         *   submit中抛出异常:不管提交的是Runnable还是Callable类型的任务，如果不对返回值Future调用get()方法，都会吃掉异常
         *   其中的call能够抛出Exception异常，所以不管是CheckedException还是UncheckedException，直接抛出即可。
         */
        //关闭线程池
        service.shutdown();
    }
}

