package daemontest;

/**
 * @author CTX
 * @since 2019/10/25 14:35
 * 只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就全部工作；只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作。
 * Daemon的作用是为其他线程的运行提供便利服务，守护线程最典型的应用就是 GC (垃圾回收器)，它就是一个很称职的守护者。
 *
 * User和Daemon两者几乎没有区别，唯一的不同之处就在于虚拟机的离开：如果 User2 Thread已经全部退出运行了，只剩下Daemon Thread存在了，虚拟机也就退出了。
 * 因为没有了被守护者，Daemon也就没有工作可做了，也就没有继续运行程序的必要了。
 */
class User extends Thread{
    public void run(){
        for(int i = 0; i < 5;i++){
            System.out.println("用户线程"+i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Daemon implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 999999999;i++){
            System.out.println("守护线程"+i);
            try {
                Thread.sleep(50);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class DaemonTest {
    public static void main(String[] args) {
        Thread usertr = new User();
        Thread daemontr = new Thread(new Daemon());
        daemontr.setDaemon(true);
        usertr.start();
        daemontr.start();
    }
}
