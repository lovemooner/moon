package love.moon.algorithm.sort.select;

import love.moon.algorithm.sort.ISort;

/**
 * 选择排序-直接选择排序
 *
 * @author dongnan
 * @date 2020/9/20 16:05
 */
public class SelectionSort implements ISort {

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int tmp = arr[i];
                arr[i] = arr[min]; //arr[i]是已排序队列
                arr[min] = tmp;
            }
        }
    }
}
