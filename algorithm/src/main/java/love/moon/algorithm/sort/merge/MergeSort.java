package love.moon.algorithm.sort.merge;

import love.moon.algorithm.sort.ISort;

/**
 * @author lovemooner
 * @date 2020/7/28 16:32
 */
public class MergeSort implements ISort {


    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        //将两个有序子数组合并操作
        int[] temp = new int[right - left + 1];
        int t = 0;//临时数组指针
        int i = left,j = mid + 1;
        while (i <= mid && j <= right) {
            temp[t++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {//将左边剩余元素
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[right--] = temp[--t];
        }
    }

}