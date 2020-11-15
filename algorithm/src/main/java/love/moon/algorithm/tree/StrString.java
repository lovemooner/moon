package love.moon.algorithm.tree;

/**
 * @author : ndong
 * date : 2020/11/15 16:17
 * desc :
 */
public class StrString {

    public static int strStr1(String haystack, String needle) {
        if (needle.length()== 0) {
            return 0;
        }
        char[] arr1 = haystack.toCharArray();
        char[] arr2 = needle.toCharArray();
        for (int i = 0; i <= arr1.length - arr2.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] != arr2[j]) {
                    if(j!=0){
                        i=i-j;
                    }
                    break;
                }
                if (j == arr2.length-1) {
                    return i - j;
                }
                i++;
            }

        }
        return -1;
    }

    public static int strStr(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        for (int i = 0; i <= n - L; ++i) {
            if (haystack.substring(i, i + L).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String haystack = "mississippi", needle = "issip";
//        String haystack = "hello", needle = "ll";
//        String haystack = "mississippi", needle = "pi";
        System.out.println(strStr(haystack, needle));
    }
}
