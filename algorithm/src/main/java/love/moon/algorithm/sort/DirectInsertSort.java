package love.moon.algorithm.sort;

/**
 * 插入排序-直接插入排序
 * @author dongnan
 * @date 2020/9/20 16:05
 */
public class DirectInsertSort {

    /**
     * 直接插入排序
     */
    private static void insertSort(int[] arr) {
        int j; // 已排序列表下标
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                int temp = arr[i];
                for (j = i - 1; j >= 0 && temp < arr[j]; j--) {
                    arr[j + 1] = arr[j]; //后移
                }
                arr[j + 1] = temp; // 插入待排序元素
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {6, 5, 3, 1, 8, 7, 2, 4};
        System.out.println("排序之前：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        insertSort(arr);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
