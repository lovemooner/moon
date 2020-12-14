package love.moon.algorithm.leetcode;

/**
 * @author : ndong
 * date : 2020/12/5 22:21
 * desc :
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
       double r= findMedianSortedArrays(new int[]{},new int[]{});
        System.out.println(r);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int [] nums3=new int[nums1.length+nums2.length];
        int i= nums1.length-1,j=nums2.length-1;
        int t=nums3.length-1;
        if(t==0) return 0;
        while(i>=0&&j>=0){
            nums3[t--]=nums1[i]>nums2[j]?nums1[i--]:nums2[j--];
        }
        while(i>=0){
            nums3[t--]=nums1[i--];
        }
        while(j>=0){
            nums3[t--]=nums2[j--];
        }
        if(nums3.length%2==1) {
            return nums3[nums3.length/2];
        }else{
            int sum= nums3[(nums3.length-1)/2]+nums3[(nums3.length-1)/2+1];
            return (double) sum/2;
        }

    }
}
