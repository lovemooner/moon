package love.moon.algorithm.leetcode;

public class CanPlaceFlowers {

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        if (flowerbed.length == 1 && flowerbed[0] == 0) {
            count++;
        } else {
            for (int i = 0; i < flowerbed.length; i++) {
                if (i == 0) {
                    if (flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                        flowerbed[i] = 1;
                        count++;
                    }
                    continue;
                }
                if (i == (flowerbed.length - 1)) {
                    if (flowerbed[i] == 0 && flowerbed[i - 1] == 0) {
                        flowerbed[i] = 1;
                        count++;
                    }
                    continue;
                }
                if (flowerbed[i - 1] + flowerbed[i] + flowerbed[i + 1] == 0) {
                    flowerbed[i] = 1;
                    count++;
                    continue;
                }
            }
        }
        return count >= n;
    }

    public static boolean canPlaceFlowers1(int[] flowerbed, int n) {
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i++] = 1;
                count++;
            }
            if(count>=n)
                return true;
            i++;
        }
        return false;
    }

    public static void main(String[] args) {
//        int [] nums={1,0,0,0,1};
        int[] nums = {1, 0, 0, 0, 1, 0, 0};
        boolean result = canPlaceFlowers1(nums, 2);
        System.out.println(result);
    }


}
