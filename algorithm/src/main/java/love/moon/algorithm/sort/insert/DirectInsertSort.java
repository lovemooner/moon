package love.moon.algorithm.sort.insert;

import love.moon.algorithm.sort.ISort;

/**
 * 插入排序-直接插入排序
 * @author dongnan
 * @date 2020/9/20 16:05
 */
public class DirectInsertSort implements ISort {

    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                int temp = arr[i];
                int j=i;
                for (; j >= 1 && temp < arr[j-1]; j--) {
                    arr[j] = arr[j-1]; //后移
                }
                arr[j] = temp; // 插入待排序元素
            }
        }
    }

}
