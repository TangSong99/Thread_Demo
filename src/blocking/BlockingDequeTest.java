package blocking;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author CTX
 * @since 2019/11/16 16:22
 * 阻塞栈
 */
public class BlockingDequeTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingDeque blockingDeque = new LinkedBlockingDeque(20);
        for (int i = 0; i < 30; i++) {
            blockingDeque.putFirst(i);
            System.out.println("向阻塞栈中加入元素" + i);
            blockingDeque.remove(1);
        }
        System.out.println("执行不到这里-----");
    }
}
