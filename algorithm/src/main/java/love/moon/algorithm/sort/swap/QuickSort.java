package love.moon.algorithm.sort.swap;

import love.moon.algorithm.sort.ISort;

/**
 * 交换排序-快速排序
 *
 * @author dna
 * @date 2020/7/28 15:35
 */
public class QuickSort implements ISort {

    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int index = partition(arr, left, right);
            quickSort(arr, left, index - 1);
            quickSort(arr, index + 1, right);
        }
        return arr;
    }
    private int partition(int[] arr, int left, int right) {
        int pivot = left,slow  = left + 1;
        for (int i = slow ; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, slow);
                slow++;
            }
        }
        swap(arr, pivot, slow - 1);
        return slow - 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
