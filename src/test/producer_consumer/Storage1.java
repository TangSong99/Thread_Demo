package test.producer_consumer;

import java.util.LinkedList;

/**
 * @author CTX
 * @since 2019/10/28 20:30
 * 生产者消费者问题
 * wait/notify/notifyAll 实现
 */
public class Storage1 implements AbstractStorage {
    //仓库最大容量
    private final int MAX_SIZE = 100;
    //仓库存储的载体
    private LinkedList list = new LinkedList();
    /**
    * ArrayList是线性表（数组）
    * get()直接读取下标 O(1)
    * add(E)直接后面添加 O(1)
    * add(index,E) 在第几个后面插入，后面元素后移 O(n)
    * remove() 删除元素，后面元素逐个移动 O(n)
     *
     *LinkedList是链表的操作
     * get()获取第n个，依次遍历 O(n)
     * add(E) 添加到尾元素 O(1)
     * add(index,E)添加第几个元素之后，需要查到第几个元素，直接指针指向操作 O(n)
     * remove()删除元素，直接指针指向操作 O(1)
    * */
    //生产产品
    public void produce(int num){
        //同步
        synchronized (list){
            //仓库剩余的容量不足以存放即将要生产的数量，暂停生产
            while(list.size()+num > MAX_SIZE){
                System.out.println("【要生产的产品数量】:" + num + "\t【库存量】:"
                        + list.size() + "\t暂时不能执行生产任务!");

                try {
                    //条件不满足，生产阻塞
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for(int i=0;i<num;i++){
                list.add(new Object());
            }

            System.out.println("【已经生产产品数】:" + num + "\t【现仓储量为】:" + list.size());

            list.notifyAll();
        }
    }

    //消费产品
    public void consume(int num){
        synchronized (list){

            //不满足消费条件
            while(num > list.size()){
                System.out.println("【要消费的产品数量】:" + num + "\t【库存量】:"
                        + list.size() + "\t暂时不能执行消费任务!");

                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //消费条件满足，开始消费
            for(int i=0;i<num;i++){
                list.remove();
            }

            System.out.println("【已经消费产品数】:" + num + "\t【现仓储量为】:" + list.size());

            list.notifyAll();
        }
    }
}
