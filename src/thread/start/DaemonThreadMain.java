package thread.start;

public class DaemonThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "main() start");

        DeamonThread deamonThread = new DeamonThread();
        deamonThread.setDaemon(true); // 데몬 스레드 여부 -> start 실행 전 결정
        deamonThread.start();

        System.out.println(Thread.currentThread().getName() + "main() end");
    }

    static class DeamonThread extends Thread {
        // throws 불가능
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": run()");
            try {
                // 10초간 실행
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + ": run() end");
        }
    }
}
