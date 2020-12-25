package love.moon.algorithm.sort.select;

import love.moon.algorithm.sort.ISort;

/**
 * 选择排序-堆排序
 *
 * @author dna
 * @date 2020/7/28 18:27
 */
public class HeapSort implements ISort {

    /**
     * 本例采用 大顶堆
     *
     * @param arr
     */
    public void sort(int[] arr) {
        //1.构建大顶堆，从第一个非叶子结点从下至上，从右至左调整结构
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap1(arr, i, arr.length);
        }
        //2.交换+调整
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i); //交换堆顶元素与末尾元素
            adjustHeap1(arr, 0, i);//调整根节点
        }
    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr
     * @param parent
     * @param length
     */
    public static void adjustHeap(int[] arr, int parent, int length) {
        int temp = arr[parent];
        //从parent的左子结点开始，从上往下
        int child = parent * 2 + 1;
        for (; child < length; child = child * 2 + 1) {
            if (child + 1 < length && arr[child] < arr[child + 1]) {//如果左子结点小于右子结点，j指向右子结点
                child++;
            }
            if (arr[child] > temp) {
                arr[parent] = arr[child];
                parent = child;
            } else {
                break;
            }
        }
        arr[parent] = temp;
    }

    public static void adjustHeap1(int[] arr, int parent, int length) {
        int temp = arr[parent];
        while ((parent * 2 + 1) < length) {
            int child = parent * 2 + 1;
            if (child + 1 < length && arr[child] < arr[child + 1]) {
                child++;
            }
            if (arr[child] <= temp) {
                break;
            }
            arr[parent] = arr[child];
            parent = child;
        }
        arr[parent] = temp;


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