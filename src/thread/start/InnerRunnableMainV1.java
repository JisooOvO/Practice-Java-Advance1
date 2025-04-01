package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV1 {

    public static void main(String[] args) {
        log("main() start");

        MyRunner runnable = new MyRunner();
        Thread thread = new Thread(runnable);
        thread.start();

        log("main() end");
    }

    static class MyRunner implements Runnable {
        @Override
        public void run() {
            log("run()");
        }
    }
}
