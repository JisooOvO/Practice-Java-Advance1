package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockMain {

    public static void main(String[] args) {
        // SpinLockBad spinLock = new SpinLockBad();
        SpinLock spinLock = new SpinLock();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                spinLock.lock();

                try {
                    // critical section
                    log("비즈니스 로직 실행");

                    // cas는 while문이므로 -> CPU를 계속 사용하면서 대기
                    // sleep(1);

                } finally {
                    spinLock.unlock();
                }

            }
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();
    }

}
