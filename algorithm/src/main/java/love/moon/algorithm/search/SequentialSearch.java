package love.moon.algorithm.search;

public class SequentialSearch {

    public static void main(String[] args) {
        int arr[] = {1, 3, 9, 12, 14, 27, 31, 40, 45, 50};
        int result = search(arr, 3);
        System.out.println(result);

    }

    public static int search(int[] arr, int keyword) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == keyword) {
                return i;
            }
        }
        return -1;
    }

}
