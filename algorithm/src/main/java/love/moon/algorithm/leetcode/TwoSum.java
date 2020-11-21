package love.moon.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : ndong
 * date : 2020/11/8 19:48
 * desc :
 */
public class TwoSum {



    /**
     * 暴力求解
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        int [] result=new int[2];
        int length=nums.length;
        for(int i=0;i<length;i++){
            if(i+1==length){
                return result;
            }
            for(int j=i+1;j<length;j++){
                if(nums[i]+nums[j]==target) {
                    result[0]=i;
                    result[1]=j;
                }
            }
        }
        return result;
    }


    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map= new HashMap<>(nums.length);
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i])){
                return new int[]{i,map.get(target-nums[i])};
            }else{
                map.put(nums[i],i);
            }

        }
        return null;
    }

    public static void main(String []args){
        int[] testCase={1,7,11,5};
        int[] testCase2={3,2,4};
        int[] result= twoSum(testCase2,6);
        System.out.println(result[0]+","+result[1]);
    }
}
