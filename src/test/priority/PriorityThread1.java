package test.priority;

/**
 * @author CTX
 * @since 2019/10/28 21:35
 */
public class PriorityThread1 {
    public static void main(String[] args) throws InterruptedException {
        /**
         *功能描述
         * 运行thread1.join(),这是会让主线程mian等待新的线程thread1执行完了，
         * 再执行主线程mian下面的代码，thread1.join()是然主线程main wait。
         * @author CTX
         * @date 2019/10/28
         */
        Thread thread1 = new Thread(new Thread1());
        thread1.start();
        thread1.join();
        Thread thread2 = new Thread(new Thread2());
        thread2.start();
        thread2.join();
        Thread thread3 = new Thread(new Thread3());
        thread3.start();

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
