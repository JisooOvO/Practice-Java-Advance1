package thread.executor.test;

import java.util.List;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class NewOrderService {

    private final ExecutorService es = Executors.newFixedThreadPool(10);

    public void order(String orderNo) throws ExecutionException, InterruptedException {
        InventoryWork inventoryWork = new InventoryWork(orderNo);
        ShippingWork shippingWork = new ShippingWork(orderNo);
        AccountingWork accountingWork = new AccountingWork(orderNo);

        // 작업들을 ExecutorService에 제출
        // Future<Boolean> inventoryFuture = es.submit(inventoryWork);
        // Future<Boolean> shippingFuture = es.submit(shippingWork);
        // Future<Boolean> accountingFuture = es.submit(accountingWork);
        List<Future<Boolean>> futures = es.invokeAll(List.of(inventoryWork, shippingWork, accountingWork));

        // 작업 대기
        boolean result = true;
        for (Future<Boolean> future : futures) {
            result &= future.get(); // AND
        }

        // 결과 확인
        if (result) {
            log("모든 주문 처리가 성공적으로 완료되었습니다.");
        } else {
            log("일부 작업이 실패했습니다.");
        }

        es.close();
    }

    static class InventoryWork implements Callable<Boolean> {
        private final String orderNo;

        public InventoryWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("재고 업데이트: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class ShippingWork implements Callable<Boolean> {
        private final String orderNo;

        public ShippingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("배송 시스템 알림: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class AccountingWork implements Callable<Boolean> {
        private final String orderNo;

        public AccountingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("회계 시스템 업데이트: " + orderNo);
            sleep(1000);
            return true;
        }
    }
}