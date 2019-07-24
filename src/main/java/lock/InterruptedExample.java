package lock;

/**
 * @Author liliang
 * @Date 2019/7/22 18:34
 * @Description : 测试interrupted
 **/
public class InterruptedExample {

    public static void main(String[] args) {
        System.out.println("主线程执行...........");

        Thread thread1 = new Thread(()->{
            try {
                System.out.println("thread1线程开始执行任务...................");
                Thread.sleep(1000);
                System.out.println("thread1线程结束执行任务...................");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();

        Thread thread2 = new Thread(()->{
            System.out.println("thread2线程执行任务！");
            thread1.interrupt();
        });
        thread2.start();

        Thread thread3 = new Thread(()->{
            System.out.println("---->thread3线程执行任务！");
        });

        thread3.start();



        System.out.println(thread1.isInterrupted());


    }
}
