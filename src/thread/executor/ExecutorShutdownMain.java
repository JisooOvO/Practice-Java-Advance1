package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class ExecutorShutdownMain {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("longTask", 100_000)) ;
        printState(es);
        log("== shutdown 시작 ==");
        shutdownAndAwaitTermination(es);
        log("== shutdown 완료 ==");
    }

    private static void shutdownAndAwaitTermination(ExecutorService es) {
        es.shutdown(); // non-blocking 새로운 작업은 받지 않고 이미 있는 작업을 처리하고 종료

        try {
            // shutdown() 대기 중인 작업 완료까지 기다림
            if(!es.awaitTermination(10, TimeUnit.SECONDS)) {
                // 정상 종료가 너무 오래 걸리면
                log("서비스 정상 종료 실패 -> 강제 종료 시도");
                es.shutdownNow();
                // shutdownNow() 작업 취소될 때 까지 대기
                if(!es.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("서비스가 종료되지 않았습니다."); // 서비스 종료에 문제가 발생 -> 인터럽트가 되지 않음
                }
            }
        } catch (InterruptedException e) {
            // awaitTermination()으로 대기 중인 현재 스레드가 인터럽트 될 수 있다.
            es.shutdownNow();
        }
    }

}
