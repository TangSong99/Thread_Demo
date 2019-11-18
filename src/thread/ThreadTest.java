package thread;

/**
 * @author CTX
 * @since 2019/10/25 11:23
 */
class ThreadDemo extends Thread{
    private Thread thread;
    private String threadName;

    ThreadDemo( String name) {
        threadName = name;
        System.out.println("Creating :" +  threadName );
    }

    public void run() {
        System.out.println("Running :" +  threadName );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread :" +  threadName + " interrupted.");
        }
        System.out.println("Thread :" +  threadName + " exiting.");
    }

    public void start () {
//        System.out.println("Starting :" +  threadName );
//        thread.start ();
        /**
         * 获取线程中的异常
         */
        if (thread == null) {
            thread = new Thread (this, threadName);
            try{
                thread = new Thread (this, threadName);
                thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println("catch到了");
                    }
                });
                thread.start();
            }catch (Exception e){
                System.out.println("catch不到");
            }
        }
    }
}

public class ThreadTest {
    public static void main(String args[]) {
        ThreadDemo T1 = new ThreadDemo( "Thread-1");
//        T1.setPriority(1);
        T1.start();

        ThreadDemo T2 = new ThreadDemo( "Thread-2");
//        T2.setPriority(10);
        T2.start();
    }
}