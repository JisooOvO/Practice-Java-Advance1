package thread.start;

public class HelloThreadMain {

    public static void main(String[] args) {
        // 현재 실행중인 스레드 (main)
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloThread helloThread = new HelloThread();

        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");

        // 스레드에 스택 공간을 할당하면서 스레드를 시작하는 메서드
        // main 스레드에서 Thread-0 스레드를 실행 요청(start)
        // -> Thread-0 스레드에서 run 호출
        helloThread.start();

        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }

}
