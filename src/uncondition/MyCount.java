package uncondition;

/**
 * @author CTX
 * @since 2019/11/16 17:02
 *  普通银行账户，不可透支
 */
public class MyCount {
    private String oid;                        //账号
    private int cash;                            //账户余额

    MyCount(String oid, int cash) {
        this.oid = oid;
        this.cash = cash;
    }

    /**
     * 存款
     *
     * @param x        操作金额
     * @param name 操作人
     */
    public synchronized void saving(int x, String name) {
        if (x > 0) {
            cash += x;                    //存款
            System.out.println(name + "存款" + x +"，当前余额为" + cash);
        }
        notifyAll();            //唤醒所有等待线程。
    }

//同步代码块
//    public void saving(int x, String name) {
//        if (x > 0) {
//            synchronized (this) {
//                cash += x;                    //存款
//                System.out.println(name + "存款" + x +"，当前余额为" + cash);
//                notifyAll();            //唤醒所有等待线程。
//            }
//        }
//    }

    /**
     * 取款
     *
     * @param x        操作金额
     * @param name 操作人
     */
    public synchronized void drawing(int x, String name) {
        if (cash - x < 0) {
            try {
                wait();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } else {
            cash -= x;                     //取款
            System.out.println(name + "取款" + x +"，当前余额为" + cash);
        }
        notifyAll();             //唤醒所有存款操作
    }
}