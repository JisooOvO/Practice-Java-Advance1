package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV1 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ParkTest(), "Thread-1");
        thread1.start();

        // 잠시 대기 -> Thread-1이 park 상태로 진입
        sleep(100);
        log("Thread-1 state : " + thread1.getState());

        log("main -> unpark(Thread1)");
        //LockSupport.unpark(thread1); // 1. unpark()
        thread1.interrupt(); // 2. interrupt()
    }

    // BLOCKING 과 다르게 WAITING 은 깨울 수 있음
    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("park 시작");
            LockSupport.park(); // WAITING
            log("park 종료, state : " + Thread.currentThread().getState());
            // WAITING 상태의 Thread에 인터럽트 발생시 깨어남
            log("인터럽트 상태 : " + Thread.currentThread().isInterrupted());
        }

    }
}
