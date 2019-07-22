package lock;

/**
 * @Author liliang
 * @Date 2019/7/22 10:21
 * @Description   验证join    thread1.join():Waits for this thread to die.  使其他线程等待直到thread1线程执行完成。
 **/
public class JOLExample {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("thread1开始执行...........");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread1.join();
        System.out.println("主线程在执行................");


        Thread thread2 = new Thread(() -> {
            System.out.println("thread2开始执行...........");
        });
        thread2.start();

    }
}
