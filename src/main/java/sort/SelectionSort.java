package sort;

public class SelectionSort {

    public void swapElements(int[] array, int i, int j) {
        if (isExistedIndexInArray(array, i) && isExistedIndexInArray(array, j)) {
            int temporal = array[i];
            array[i] = array[j];
            array[j] = temporal;
        }
    }

    public int indexLowest(int[] array, int start) {
        if (!isExistedIndexInArray(array, start)) {
            return -1;
        }

        int lowIndex = start;

        for (int i = start; i < array.length; i++) {
            if (array[i] < array[lowIndex]) {
                lowIndex = i;
            }
        }

        return lowIndex;
    }

    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int j = indexLowest(array, i);
            swapElements(array, i, j);
        }
    }

    private boolean isExistedIndexInArray(int[] array, int index) {
        return array.length > index && index > -1;
    }
}
