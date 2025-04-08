package thread.control.yield;

import static util.ThreadUtils.sleep;

public class YieldMain {

    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                // 1. Empty
                // sleep(1); // 2. sleep
                Thread.yield(); // 3. yield Runnable 상태는 유지하되 스케줄링 대기큐로 빠짐
                // 운영체제는 스레드를 Ready, Running 상태로 만듦
                // 실행할 스레드를 잠시 Running으로 변경
                // 자바는 Ready, Running 상태 구분할 수 없음
            }
        }

    }
}
