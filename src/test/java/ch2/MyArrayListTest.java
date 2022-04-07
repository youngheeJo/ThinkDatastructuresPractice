package ch2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {

    protected List<Integer> mylist;
    protected List<Integer> list;

    @BeforeEach
    void setup() throws Exception {
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        mylist = new ArrayList<>();
        mylist.addAll(list);
    }

    @Test
    void testAdd() {
        for (int i = 4; i < 20; i++) {
            mylist.add(i);
        }

        assertEquals(19, mylist.get(18));
    }

    @Test
    void testIndexOf() {
        assertAll(
                () -> assertEquals(0, mylist.indexOf(1)),
                () -> assertEquals(1, mylist.indexOf(2)),
                () -> assertEquals(2, mylist.indexOf(3)),
                () -> assertEquals(-1, mylist.indexOf(4))
        );
    }

    @Test
    void testRemove() {
        assertAll(
                () -> assertEquals(2, mylist.remove(1)),
                () -> assertEquals(2, mylist.size()),
                () -> assertEquals(3, mylist.get(1))
        );
    }

    @Test
    void testSet() {
        assertAll(
                () -> assertEquals(2, mylist.set(1, 5)),
                () -> assertEquals(1, mylist.set(0, 6)),
                () -> assertEquals(3, mylist.set(2, 7)),
                () -> assertEquals(6, mylist.get(0)),
                () -> assertEquals(5, mylist.get(1)),
                () -> assertEquals(7, mylist.get(2))
        );

        try {
            mylist.set(-1, 0);
            fail();
        } catch (IndexOutOfBoundsException e) {}

        try {
            mylist.set(4, 0);
            fail();
        } catch (IndexOutOfBoundsException e) {}

    }
}
