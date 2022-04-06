package ch1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SelectionSortTest {

    SelectionSort selectionSort;

    @BeforeEach
    public void initAll() {
        selectionSort = new SelectionSort();
    }

    @Test
    public void testSwapElements() {
        int[] array = {0, 1, 2, 3, 4};

        selectionSort.swapElements(array, 1, 3);

        assertAll("array",
                () -> assertEquals(1, array[3]),
                () -> assertEquals(3, array[1])
        );
    }

    @Test
    public void testIndexLowest() {
        int[] array = {2, 5, 11, 3, 13, 7};

        assertAll("array",
                () -> assertEquals(0, selectionSort.indexLowest(array, 0)),
                () -> assertEquals(3, selectionSort.indexLowest(array, 1))
        );
    }

    @Test
    public void testSort() {
        int[] array = {2, 5, 11, 3, 13, 7};
        int[] expected = {2, 3, 5, 7, 11, 13};

        selectionSort.sort(array);

        assertEquals(Arrays.toString(expected), Arrays.toString(array));
    }
}
