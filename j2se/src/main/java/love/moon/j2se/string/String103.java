package love.moon.j2se.string;

public class String103 {



    public static void main(String[] args) {
        String s1="Hollis";

        String s2=new String(s1+"b");
        String s3="ab";
        System.out.println(s3==s2.intern());
    }
}
