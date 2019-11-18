package runnable;

/**
 * @author CTX
 * @since 2019/10/25 10:20
 */
class RunnableDemo implements Runnable{
    private Thread thread;
    private String threadName;

    RunnableDemo(String name){
        this.threadName = name;
        System.out.println("Creating:" + threadName);
    }

    @Override
    public void run() {
        /**
         *把需要处理的代码放到run()方法中，start()方法启动线程将自动调用run()方法，
         * 这个由java的内存机制规定的。并且run()方法必需是public访问权限，返回值类型为void。
         */
        System.out.println("Running:"+ threadName);
        try {
            for(int i = 4;i > 0;i--){
                System.out.println("Thread:"+threadName+","+i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread:" +  threadName + " interrupted.");//线程中断
        }
        System.out.println("Thread:" +  threadName + " exiting.");
    }

    public void start(){
        System.out.println("Staring:"+threadName);
        if(thread==null){
            thread = new Thread(this,threadName);
            thread.start();
        }
    }
}

public class RunnableTest{
    public static void main(String[] args) {
        RunnableDemo R1 = new RunnableDemo("Thread-1");
        R1.start();

        RunnableDemo R2 = new RunnableDemo( "Thread-2");
        R2.start();
    }
}
