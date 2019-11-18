package test.producer_consumer;

/**
 * @author CTX
 * @since 2019/10/28 20:32
 */
public interface AbstractStorage {
    void consume(int num);
    void produce(int num);
}
