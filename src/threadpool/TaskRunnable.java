package threadpool;

/**
 * @author CTX
 * @since 2019/10/26 20:00
 */
public class TaskRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("自定义线程任务在执行"+i);
        }
    }
}
