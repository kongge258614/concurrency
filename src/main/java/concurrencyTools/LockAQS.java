package concurrencyTools;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author liliang
 * @Date 2021/3/2 17:44
 * @Description
 **/
public class LockAQS {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("业务逻辑处理............");
        } finally {
            lock.unlock();
        }

    }


}
