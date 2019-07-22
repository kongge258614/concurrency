package lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author liliang
 * @Date 2019/7/22 16:39
 * @Description  测试偏向锁膨胀为轻量级锁，再膨胀为重量级锁的过程
 **/
public class JOLExample003 {
    public static void main(String[] args) throws InterruptedException {
        Student student = new Student();
        Thread thread1 = new Thread(()->{
            synchronized (student){
                System.out.println("thread1线程正在执行.................");
                System.out.println(ClassLayout.parseInstance(student).toPrintable());
            }
        });

        thread1.start();
        thread1.join();

        Thread thread = new Thread(()->{
            System.out.println("thread执行.....");
        });
        thread.start();

        Thread thread2 = new Thread(()->{
            try {
                synchronized (student){
                    Thread.sleep(10);
                    System.out.println("thread2正在执行.............");
                    System.out.println(ClassLayout.parseInstance(student).toPrintable());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();

        Thread thread3 = new Thread(()->{
            try {
                synchronized (student){
                    Thread.sleep(100);
                    System.out.println("thread3正在执行............");
                    System.out.println(ClassLayout.parseInstance(student).toPrintable());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread3.start();

    }
}
