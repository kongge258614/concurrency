package lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author liliang
 * @Date 2019/7/22 16:09
 * @Description：测试当线程获取到偏向锁之后，且线程执行完毕后，是否会释放偏向锁
 *
 * 一、关闭偏向锁
 *              对象的锁变化过程：无锁--》轻量级锁--》释放锁
 *              01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *              90 f2 c3 1f (10010000 11110010 11000011 00011111) (532935312)
 *              01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *二、开始偏向锁，且延迟时间为0
 *              对象锁的变化过程：无锁（偏向状态）--》轻量级锁--》轻量级锁（并未释放）
 *              05 00 00 00 (00000101 00000000 00000000 00000000) (5)
 *              05 60 65 1f (00000101 01100000 01100101 00011111) (526737413)
 *              05 60 65 1f (00000101 01100000 01100101 00011111) (526737413)
 *
 **/
public class JOLExample002 {
    public static void main(String[] args) throws InterruptedException {

        Student student = new Student();
        System.out.println("默认开启偏向锁，默认偏向锁延迟");
        System.out.println(ClassLayout.parseInstance(student).toPrintable());

        Thread thread1 = new Thread(()->{
            synchronized (student){
                System.out.println("thread1线程开始执行................");
                System.out.println(ClassLayout.parseInstance(student).toPrintable());
            }
        });
        thread1.start();
        thread1.join();
        System.out.println("主线程继续执行.............");

        System.out.println(ClassLayout.parseInstance(student).toPrintable());
    }
}
