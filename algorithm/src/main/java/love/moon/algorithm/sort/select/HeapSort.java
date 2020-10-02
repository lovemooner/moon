package love.moon.algorithm.sort.select;

import love.moon.algorithm.sort.ISort;

import java.util.Arrays;

/**
 * 选择排序-堆排序
 * @author dna
 * @date 2020/7/28 18:27
 */
public class HeapSort implements ISort {

    public  void sort(int[] arr) {
        //1.构建大顶堆，从第一个非叶子结点从下至上，从右至左调整结构
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, i);//重新对堆进行调整
        }
    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (j + 1 < length && arr[j] < arr[j + 1]) {//如果左子结点小于右子结点，k指向右子结点
                j++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (arr[j] > temp) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}