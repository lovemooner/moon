package love.moon.algorithm.search;

/**
 * @author moon
 * @date 2020/5/30 22:20
 */
public class BinarySearch {

    public static void main(String[] args) {
        System.out.println(5%3);

        int arr[] = {1, 3, 9, 12, 14, 27, 31, 40, 45, 50};
        int result = search(arr, 3);
        System.out.println(result);
    }

    /**
     * target : 9
     * ####### case 2个数 mid是左边的 ##############
     * 8 10
     * 0  1
     * 个数变化：2->1
     * ####### case 3个数 ##############
     * 8 10 11
     * 0  1 2
     * 个数变化: 3->1
     * ####### case 4个数 ##############
     * 6 8 11 12
     * 0 1 2   3
     * 个数变化:4->2->1
     * ####### case 5个数 ##############
     * 6 7 8 10 11
     * 0 1 2  3  4
     * 个数变化: 5->2>1
     * ####### case 6个数 ##############
     * 1 3 5 6 8 10
     * 0 1 2 3 4  5
     * 个数变化:6->3->1
     * ####### case 7个数 ##############
     * 1 3 5 6 8 10 11
     * 0 1 2 3 4  5  6
     * 个数变化:7->3->1
     * ####### case2 8个数 ##############
     * 1 3 5 6 8 10 11 12
     * 0 1 2 3 4  5  6  7
     * 个数变化：8->4->2->1
     * ####### case 9个数 ##############
     * 1 3 5 6 8 10 11 12 13
     * 0 1 2 3 4  5  6  7  8
     * 个数变化：9->4->2->1
     * ####### case 10个数 ##############
     * 1 3 5 6 8 10 11 12 13 14
     * 0 1 2 3 4  5  6  7  8  9
     * 个数变化：10->5->2->1
     *####### case 11个数 ##############
     * 1 3 5 6 7 8 11 12 13 14 15
     * 0 1 2 3 4 5  6  7  8  9 10
     * 个数变化：11->5-2->1
     *
     * so
     * 17->8->4->2->1
     * 18->9->4->2->1
     * 20->10->5->2->1
     * 查询次数：
     * log2N
     */
    static int search(int [] arr, int keyword) {
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
