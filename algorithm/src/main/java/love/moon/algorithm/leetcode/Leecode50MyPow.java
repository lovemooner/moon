package love.moon.algorithm.leetcode;

public class Leecode50MyPow {

    public static void main(String[] args) {
        Leecode50MyPow sol=new Leecode50MyPow();
        System.out.println(sol.myPow(2.00000,10));
    }

    public double myPow(double x, int n) {
        if(n==0) return 0;
        int c=Math.abs(n);
        double k=x;
        while(--c>0){
            x=x*k;
        }
        return n<0?1/x:x;
    }
}
