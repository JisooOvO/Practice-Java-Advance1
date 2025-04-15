package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CallableMainV1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        // es.execute(); -> Runnable
        Future<Integer> future = es.submit(new MyCallable()); // -> Callable 즉시 반환

        // future.get() -> 호출 시 작업을 완료 할 수 도, 아닐 수 도
        // -> 즉시 실행 되는게 아님, 미래의 어떤 시점에 실행
        Integer result = future.get();
        log("result value = " + result);
        es.close();
    }

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            log("Callable 시작");
            sleep(2000);
            int value = new Random().nextInt(10);
            log("create value = " + value);
            log("Callable 완료");
            return value;
        }
    }
}
