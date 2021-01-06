package concurrencyTools;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Author: liliang
 * @Date: 2019/3/26 16:58
 * @Description:  CyclicBarrier:
 */
public class CyclicBarrierTest {

    private static final int NUMBER = 7;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER,() -> {
            System.out.println(Thread.currentThread().getName()+"我来执行!");
        });

        for (int i= 1;i<=NUMBER;i++){
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"来报道");
                    cyclicBarrier.await();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {

                }
            },String.valueOf(i)).start();

        }


    }
}
