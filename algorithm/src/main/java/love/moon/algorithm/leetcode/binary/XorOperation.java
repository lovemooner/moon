package love.moon.algorithm.leetcode.binary;

/**
 * @author : ndong
 * date : 2020/11/27 16:56
 * desc :
 */
public class XorOperation {

    public int xorOperation(int n, int start) {
        int [] nums=new int[n];
        int result=0;
        for(int i=0;i<n;i++){
            nums[i]=start + 2*i;
            if(i>0){
                nums[i]=nums[i]^nums[i-1];
            }
        }
       return nums[n-1];
    }
}
