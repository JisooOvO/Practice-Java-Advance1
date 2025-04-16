package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        if(executorService instanceof ThreadPoolExecutor poolExecutor) {
            int pool = poolExecutor.getPoolSize(); // 스레드 수
            int active = poolExecutor.getActiveCount(); // 실행 중인 스레드 수
            int queued = poolExecutor.getQueue().size(); // 작업 수
            long completedTask = poolExecutor.getCompletedTaskCount(); // 완료된 작업 수
            log(
            "[ pool = " + pool +
                ", active = " + active +
                ", queuedTasks = " + queued +
                ", completedTask = " + completedTask + " ]"
            );
        } else{
            log(executorService);
        }
    }

    public static void printState(ExecutorService executorService, String taskName) {
        if(executorService instanceof ThreadPoolExecutor poolExecutor) {
            int pool = poolExecutor.getPoolSize(); // 스레드 수
            int active = poolExecutor.getActiveCount(); // 실행 중인 스레드 수
            int queued = poolExecutor.getQueue().size(); // 작업 수
            long completedTask = poolExecutor.getCompletedTaskCount(); // 완료된 작업 수
            log(
            taskName + " -> [ pool = " + pool +
                ", active = " + active +
                ", queuedTasks = " + queued +
                ", completedTask = " + completedTask + " ]"
            );
        } else{
            log(executorService);
        }
    }
}
