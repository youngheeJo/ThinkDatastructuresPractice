package list;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

public class MyLinkedListTest extends MyArrayListTest {
    @BeforeEach
    void setup() {
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        mylist = new MyLinkedList<>();
        mylist.addAll(list);
    }
}
