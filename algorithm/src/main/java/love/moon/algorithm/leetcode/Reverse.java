package love.moon.algorithm.leetcode;

public class Reverse {
    public static int reverse(int x) {
        int reverseNum=0;
        while(x!=0){
            if((reverseNum>Integer.MAX_VALUE/10)||(reverseNum<Integer.MIN_VALUE/10)){
                return 0;
            }
            reverseNum=reverseNum*10+x%10;
            x=x/10;
        }
        return reverseNum;
    }

    public static void main(String[] args) {
//        System.out.println(reverse(1534236469));
        System.out.println(reverse(-2147483412));
    }
}
