package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV1 implements BankAccount{

    private int balance;

    public BankAccountV1(int initBalance) {
        this.balance = initBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());

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

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }

}
