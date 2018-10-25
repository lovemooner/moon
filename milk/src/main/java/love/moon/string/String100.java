package love.moon.string;

public class String100 {

    public static void main(String[] args) {
        String s1 = "Hollis";
        String s2 = new String("Hollis");
        String s3 = new String("Hollis").intern();

//        System.out.println(s1 == s2);
//        System.out.println(s1 == s3);
    }
}
