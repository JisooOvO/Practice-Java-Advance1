package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV4 implements BankAccount {

    private final Lock lock = new ReentrantLock();
    private int balance;

    public BankAccountV4(int initBalance) {
        this.balance = initBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());

        // === 임계 영역 시작 ===
        lock.lock(); // ReentrantLock 이용
        try {
            log("[검증 시작] 출금액 : " + amount + ", 잔액 : " + balance);
            // 잔고 < 출금액
            if (balance < amount) {
                log("[검증 실패]");
                return false;
            }
            log("[검증 완료] 출금액 : " + amount + ", 잔액 : " + balance);

            // 잔고 >= 출금액
            sleep(1000); // 출금에 걸리는 시간으로 가정
            balance -= amount;
            log("[출금 완료] 출금액 : " + amount + ", 잔액 : " + balance);
        } finally {
            lock.unlock(); // 무조건 unlock이 발생
        }
        // === 임계 영역 종료 ===

        log("거래 종료");
        return true;
    }

    @Override
    public  int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

}
