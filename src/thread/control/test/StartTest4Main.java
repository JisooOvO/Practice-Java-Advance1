package thread.control.test;

import static util.MyLogger.log;

public class StartTest4Main {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new PrintWork("A", 1000), "Thread-A");
        Thread thread2 = new Thread(new PrintWork("B", 500));

        thread1.start();
        thread2.start();
    }

    static class PrintWork implements Runnable {

        private final String content;
        private final int sleepMs;

        public PrintWork(String content, int sleepMs) {
            this.content = content;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run() {
            while (true) {
                log(content);
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
