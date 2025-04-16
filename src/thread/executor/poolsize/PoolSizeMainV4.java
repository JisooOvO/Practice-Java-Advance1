package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class PoolSizeMainV4 {

    // static final int TASK_SIZE = 1100; // 일반
    // static final int TASK_SIZE = 1200; // 긴급
    static final int TASK_SIZE = 1201; // 거절


    public static void main(String[] args) {
        // 사용자 정의 풀 전략 -> 초과 스레드 + 큐 사이즈 수 넘기면 거절
        // 큐 사이즈가 무한대면 초과 스레드가 생성이 안됨
        ExecutorService es = new ThreadPoolExecutor(
                100,
                200,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000)
        );

        printState(es);

        long startMs = System.currentTimeMillis();

        for (int i = 1; i <= TASK_SIZE; i++) {
            String taskName = "task" + i;
            try {
                es.execute(new RunnableTask(taskName));
                printState(es, taskName);
            } catch (RejectedExecutionException e) {
                log(taskName + " -> " + e);
            }
        }

        es.close();
        long endMs = System.currentTimeMillis();
        log("time : " + (endMs - startMs));
    }

}
