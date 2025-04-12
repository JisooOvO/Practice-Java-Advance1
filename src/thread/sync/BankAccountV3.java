package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV3 implements BankAccount{

    private int balance;

    public BankAccountV3(int initBalance) {
        this.balance = initBalance;
    }

    // synchronized는 편리하지만 몇 가지 단점이 존재
    // - 무한 대기 : BLOCKED 상태는 lock이 풀릴 때까지 무한 대기(타임아웃, 인터럽트 X)
    // - 공정성 : 어떤 스레드가 lock을 획득 할 지 모름
    // => 더 유연하고 세밀한 제어를 위해 java 1.5부터 java.util.concurrent 패키지 추가!

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());

        // 임계 영역은 가능한 최소한으로 -> 성능 향상
        // === 임계 영역 시작 ===
        synchronized (this) { // lock 얻어오는 인스턴스 참조 값
            log("[검증 시작] 출금액 : " + amount + ", 잔액 : " + balance);
            // 잔고 < 출금액
            if(balance < amount) {
                log("[검증 실패]");
                return false;
            }
            log("[검증 완료] 출금액 : " + amount + ", 잔액 : " + balance);

            // 잔고 >= 출금액
            sleep(1000); // 출금에 걸리는 시간으로 가정
            balance -= amount;
            log("[출금 완료] 출금액 : " + amount + ", 잔액 : " + balance);
        }
        // === 임계 영역 종료 ===

        log("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }

}
