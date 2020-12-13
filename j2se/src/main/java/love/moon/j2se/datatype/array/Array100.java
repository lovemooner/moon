package love.moon.j2se.datatype.array;

import org.junit.Test;

/**
 * @author : ndong
 * date : 2020/11/26 17:16
 * desc :
 */
public class Array100 {

    @Test
    public void  createArray(){
       //第一种方式：
       int [][] arr1 ={{1,2},{4,5},{6,7}};//每个元素代表一行
        System.out.println("第一行数据"+arr1[0][0]+" ,"+arr1[0][1]);
        System.out.println("第一列数据"+arr1[0][0]+" ,"+arr1[1][0]+" ,"+arr1[2][0]);
       //第二种方式；
       int[][] arr2 = new int[3][2];
        arr2[0][0] =1; //分别赋值...

       //第三种方式：第二维的长度可以动态申请
       int[][] arr3 = new int[3][];//五行的长度
       for(int i=0; i<arr3.length; ++i){
           arr3[i]=new int[2];   //列的长度每次都变化。每次都要重新申请空间(长度)
           for(int j=0; j<arr3[i].length; ++j)
               arr3[i][j]= i+j;
       }
        System.out.println("end");
    }

}
