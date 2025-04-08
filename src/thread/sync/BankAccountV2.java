package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV2 implements BankAccount{

    private int balance;

    public BankAccountV2(int initBalance) {
        this.balance = initBalance;
    }

    // synchronized - instance 단위 생성
    // 모든 객체는 자신만의 lock 을 지님 = monitor lock
    // synchronized 메서드에 진입시 해당 인스턴스의 lock 있어야 함
    // 해당 메서드를 먼저 실행하는 스레드는 lock 가져감
    // 이후 이 메서드를 실행하는 스레드는 lock 없어 획득 실패 -> BLOCKED 상태로 대기
    // BLOCKED 상태는 lock 얻을 때 까지 계속 대기 (CPU 실행 스케줄링에 들어가지 않음)
    // 메서드 실행이 끝난 뒤 lock 반환
    @Override
    public synchronized boolean withdraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());

        // === 임계 영역 시작 ===
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
        // === 임계 영역 종료 ===

        log("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }

}
