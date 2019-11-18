package condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author CTX
 * @since 2019/11/16 16:38
 * 条件变量
 *
 * 有一个账户，多个用户（线程）在同时操作这个账户，有的存款有的取款，存款随便存，取款有限制，不能透支，
 * 任何试图透支的操作都将等待里面有足够存款才执行操作。
 */
public class ConditionTest {
    public static void main(String[] args) {
        //创建并发访问的账户
        MyCount myCount = new MyCount("123456789", 10000);
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Thread t1 = new SaveThread("张三", myCount, 2000);
        Thread t2 = new SaveThread("李四", myCount, 3600);
        Thread t3 = new DrawThread("王五", myCount, 700);
        Thread t4 = new SaveThread("老张", myCount, 600);
        Thread t5 = new DrawThread("老牛", myCount, 13000);
        Thread t6 = new DrawThread("胖子", myCount, 800);
        //执行各个线程
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        //关闭线程池
        pool.shutdown();
    }
}
