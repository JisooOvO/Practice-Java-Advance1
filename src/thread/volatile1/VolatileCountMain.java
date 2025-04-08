package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileCountMain {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(1000);

        // non-volatile
        // 13:49:54.210 [     main] flag = false, count = 1473049745 in main()
        // 13:49:54.228 [     work] flag = true, count = 1500000000 in while()
        // 13:49:54.228 [     work] flag = false, count = 1500000000 종료
        myTask.flag = false;
        log("flag = " + myTask.flag + ", count = " + myTask.count + " in main()");

    }

    static class MyTask implements Runnable {

        // boolean flag = true;
        // long count;
        // 약 2배 이상의 성능 차이 -> write before read
        volatile boolean flag = true;
        long count;

        @Override
        public void run() {
            while (flag) {
                count++;
                // 1억번에 한번씩 출력
                if(count % 100_000_000 == 0) {
                    log("flag = " + flag + ", count = " + count + " in while()");
                }
            }
            log("flag = " + flag + ", count = " + count + " 종료");
        }
    }
}
