package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyLogger.log;

public class SpinLock {

    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock(){
        log("락 획득 시도");

        // 사용 여부 확인 및 값 변경을 하나로 -> 원자적 연산 (하드웨어 기능)
        while (!lock.compareAndSet(false, true)) {
            log("락 획득 실패 - 스핀 대기");
        }

        log("락 획득 완료");
    }

    public void unlock(){
        lock.set(false);
        log("락 반납 완료");
    }
}
