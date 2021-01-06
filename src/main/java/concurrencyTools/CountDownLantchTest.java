package concurrencyTools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author: liliang
 * @Date: 2019/3/26 17:26
 * @Description:
 */
public class CountDownLantchTest {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i=1;i<=5;i++){
            new Thread(
                    () -> {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            System.out.println(Thread.currentThread().getName()+"工作完成！");
                            countDownLatch.countDown();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    },String.valueOf(i)
            ).start();

        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"关门走人！");
    }
}
