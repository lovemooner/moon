package love.moon.algorithm.leetcode;

import love.moon.util.Assert;

/**
 * @author : ndong
 * date : 2020/12/3 18:51
 * desc :
 */
public class SearchMatrix {

    public static void main(String[] args) {
        int[][] matrix={{}};
        Assert.assertFalse(searchMatrix(matrix,1));
        int[][] matrix1={{1}};
        Assert.assertFalse(searchMatrix(matrix1,0));
        int[][] matrix2={{1,1}};
        Assert.assertFalse(searchMatrix(matrix2,0));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length;
        if(m==0) return false;
        int n=matrix[0].length;
        if(n==0) return false;
        if(m==1&&n==1){
            return matrix[0][0]==target;
        }
        int start=matrix[0][0];
        int end=matrix[m-1][n-1];
        int m1=m,n1=n;
        while(start<=end){
             m1=m1/2;
             n1=n1/2;
            int mid=matrix[m1][n1];
            if(mid==target){
                return true;
            }else if(mid>target&&m1<=m&&n1<=n){
                if(n1+1<=n){
                    n1=n1+1;
                }else{
                    m1=m1+1;
                    n1=0;
                }
                start=matrix[m1][n1];
            }else if(mid<target){
                if(n1-1>=0){
                    n1=n1-1;
                }else{
                    m1=m1-1;
                    n1=n;
                }
                end=matrix[m1][n1];
            }

        }
        return false;
    }
}
