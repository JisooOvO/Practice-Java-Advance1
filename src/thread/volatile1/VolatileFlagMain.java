package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread t = new Thread(myTask, "work");

        log("runFlag = " + myTask.runFlag);
        t.start();

        sleep(1000);

        log("runFlag를 false로 변경 시도");
        // 바꾼 runFlag는 main CPU 캐시 메모리에 있는 runFlag
        // 메인 메모리의 runFlag 값은 아직 변경되지 않음!
        // work 스레드의 캐시메모리 runFlag 값 역시 변경 X

        // 언제 반영되는가 ? -> CPU 설계 방식과 종류에 따라 다름
        // 주로 컨텍스트 스위칭이 될 때 캐시 메모리도 갱신
        myTask.runFlag = false;
        log("runFlag = " + myTask.runFlag);
        log("main 종료");

    }

    // 메모리 가시성(Memory visibility)
    // -> 멀티 스레드 환경에서 한 스레드가 변경한 값이 다른 스레드에 언제 보이는지에 대한 문제
    static class MyTask implements Runnable {
        // 각 스레드가 runFlag 값을 사용하면
        // CPU는 이 값을 효율적으로 처리하기 위해 runFlag를 캐시 메모리에 저장
        // -> 이후 캐시 메모리의 runFlag를 사용
        // boolean runFlag = true;

        // volatile -> 캐시 메모리를 사용하지 않고 값을 읽고 쓸 때 메인 메모리에 접근
        volatile boolean runFlag = true;

        @Override
        public void run() {
            log("task 시작");

            while (runFlag) {
                // runFlag == false일 때 탈출
                // 콘솔 출력 -> 컨텍스트 스위칭!
            }

            log("task 종료");
        }

    }
}
