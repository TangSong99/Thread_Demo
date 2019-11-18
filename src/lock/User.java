package lock;

import java.util.concurrent.locks.Lock;

/**
 * @author CTX
 * @since 2019/11/15 10:21
 * 信用卡的用户
 */
public class User implements Runnable {
    private String name;                //用户名
    private MyCount myCount;        //所要操作的账户
    private int iocash;                //操作的金额，当然有正负之分了
    private Lock myLock;                //执行操作所需的锁对象

    User(String name, MyCount myCount, int iocash, Lock myLock) {
        this.name = name;
        this.myCount = myCount;
        this.iocash = iocash;
        this.myLock = myLock;
    }

    public void run() {
        //获取锁
        myLock.lock();
        //执行现金业务
        System.out.println(name + "正在操作" + myCount +"账户，金额为" + iocash +"，当前金额为" + myCount.getCash());
        myCount.setCash(myCount.getCash() + iocash);
        System.out.println(name + "操作" + myCount +"账户成功，金额为" + iocash +"，当前金额为" + myCount.getCash());
        //释放锁，否则别的线程没有机会执行了
        myLock.unlock();
    }
}