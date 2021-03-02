package concurrencyTools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author liliang
 * @Date 2021/1/6 18:06
 * @Description
 **/
public class LockTest {
    public static void main(String[] args) {


        ThreadLocal<String> threadLocal= new ThreadLocal<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);
//        Lock lock = new ReentrantLock();

        new Thread(()->{
            threadLocal.set("world");

            System.out.println(threadLocal.get());

            threadLocal.remove();


            countDownLatch.countDown();
        },"t1").start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("......................................");

        new Thread(()->{

            threadLocal.set("hello");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(threadLocal.get());

            threadLocal.set("姚明");

            System.out.println(threadLocal.get());

            countDownLatch.countDown();

        },"t2").start();


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("代码执行完.......................");


    }

}
