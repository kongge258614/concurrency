package lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author liliang
 * @Date 2019/7/22 14:24
 * @Description  测试偏向锁
 **/
public class JOLExample001 {
    public static void main(String[] args) {
        Student student = new Student();
        System.out.println("默认开启偏向锁，默认偏向锁延迟");
        System.out.println(ClassLayout.parseInstance(student).toPrintable());

        Thread thread1 = new Thread(()->{
            synchronized (student){
                try {
                    Thread.sleep(100);
                    System.out.println("thread1线程.........");
                    System.out.println(ClassLayout.parseInstance(student).toPrintable());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(()->{
            synchronized (student){
                try {
                    Thread.sleep(100);
                    System.out.println("thread2线程.........");
                    System.out.println(ClassLayout.parseInstance(student).toPrintable());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();

    }
}
