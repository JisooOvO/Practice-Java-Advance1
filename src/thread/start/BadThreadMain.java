package thread.start;

public class BadThreadMain {

    // 스레드
    // - 사용자 스레드
    //     - 프로그램의 주요 작업 수행
    //     - 작업이 완료될 때까지 실행
    //     - 모든 사용자 스레드가 종료되면 JVM 종료
    // - 데몬 스레드
    //     - 백그라운드에서 보조 작업 수행
    //     - 모든 user 스레드 종료시 데몬 스레드 자동 종료
    public static void main(String[] args) {
        // 현재 실행중인 스레드 (main)
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloThread helloThread = new HelloThread();

        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");

        // run 직접 실행 -> main 스레드에서 실행
        // -> 멀티스레드 X
        helloThread.run();

        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }

}
