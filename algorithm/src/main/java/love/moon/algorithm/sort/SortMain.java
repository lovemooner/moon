package love.moon.algorithm.sort;

import love.moon.algorithm.sort.merge.MergeSort;
import love.moon.algorithm.sort.select.HeapSort;
import love.moon.algorithm.sort.select.TreeSelectSort;

public class SortMain {

    public static void main(String[] args) {
//        ISort sort=new DirectInsertSort();
//        ISort sort=new BubbleSort();
//        ISort sort=new QuickSort();
        ISort sort=new HeapSort();
//        ISort sort = new MergeSort();
//        ISort sort = new TreeSelectSort();

        int[] arr = {6, 5, 3, 1, 8, 7, 2, 4};
        System.out.println("排序之前：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        sort.sort(arr);
        System.out.println("\n排序之后：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
