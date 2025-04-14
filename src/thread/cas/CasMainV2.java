package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;

public class CasMainV2 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        // incrementAndGet 구현
        int resultValue1 = incrementAndGet(atomicInteger);
        System.out.println("resultValue2 = " + resultValue1);

        int resultValue2 = incrementAndGet(atomicInteger);
        System.out.println("resultValue2 = " + resultValue2);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get();
            log("getValue : " + getValue);

            // 다른 스레드가 값을 바꾼 경우 result -> false
            // 따라서 다시 while 조건을 반복...
            // 다시 읽으면 바뀐 값이 들어옴!
            result = atomicInteger.compareAndSet(getValue, getValue + 1); // 읽은 값이 같을 때 더하기
            log("result : " + result);
        }while (!result);

        return getValue+1;
    }

}
