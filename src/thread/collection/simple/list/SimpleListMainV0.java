package thread.collection.simple.list;

import java.util.ArrayList;
import java.util.List;

public class SimpleListMainV0 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        // 스레드1, 스레드2가 동시에 실행한다 가정
        list.add("A"); // 스레드 1
        list.add("B"); // 스레드 2;

        System.out.println(list);
    }

}
