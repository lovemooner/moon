package love.moon.algorithm.search;

/**
 * @author moon
 * @date 2020/5/30 22:20
 */
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {1, 3, 9, 12, 14, 27, 31, 40, 45, 50};
        int result = search(arr, 3);
        System.out.println(result);
    }

    static int search(int arr[], int keyword) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > keyword) {
                high = mid - 1;
            } else if (arr[mid] < keyword) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * 递归
     *
     * @param array
     * @param low
     * @param high
     * @param target
     * @return
     */
//    static int searchWithRecursion(int array[], int low, int high, int target) {
//        if (low > high) {
//            return -1;
//        }
//        int mid = low + (high - low) / 2;
//        if (array[mid] > target) {
//            return binarysearch(array, low, mid - 1, target);
//        }
//        if (array[mid] < target) {
//            return binarysearch(array, mid + 1, high, target);
//        }
//        return mid;
//    }

}
