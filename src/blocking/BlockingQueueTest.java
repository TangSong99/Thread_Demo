package blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author CTX
 * @since 2019/11/15 11:20
 * 阻塞队列
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        /**队列  --  有界性   --  锁  --  数据结构
         * ArrayBlockingQueue  有界  加锁  arrayList
         * LinkedBlockingQueue  有界自增  加锁 linkedList
         * PriorityBlockingQueue 无界  加锁  heap
         * DelayQueue 无界  加锁  heap
         * SynchronousQueue 有界 加锁 无
         * LinkedTransferQueue 无界 加锁 heap
         * LinkedBlockingDeque 无界 无锁 heap
         */
        BlockingQueue blockingQueue = new ArrayBlockingQueue(20);
        for (int i = 0; i < 30; i++) {
            //将指定元素添加到此队列中，如果没有可用空间，将一直等待（如果有必要）。
            blockingQueue.put(i);
            System.out.println("向阻塞队列添加元素" + i);
            if(i==19){
                System.out.println("队列剩余空间"+blockingQueue.remainingCapacity());
                blockingQueue.remove(19);
                System.out.println("队列剩余空间"+blockingQueue.remainingCapacity());
            }
        }
        System.out.println("程序到此运行结束，即将退出----");
    }
}
