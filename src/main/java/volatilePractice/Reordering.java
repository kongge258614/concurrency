package volatilePractice;

/**
 * @Author liliang
 * @Date 2021/1/4 14:17
 * @Description
 **/
public class Reordering {

    private static boolean flag;
    private static int num;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                while (!flag) {
                    Thread.yield();
                }

                System.out.println(num);
            }
        }, "t1");
        t1.start();
        num = 5;
        flag = true;
    }
}
