package love.moon.algorithm.leetcode;

public class Zconvert {

    public static String convert(String s, int numRows) {
        if(s==null||s.length()==0||numRows==1) return null;
        StringBuilder [] array=new StringBuilder[numRows];
        for(int i=0;i<array.length;i++){
            array[i]=new StringBuilder();
        }
        int dir=1;
        int index=0;
        for(char c : s.toCharArray()){
            array[index].append(c);
            index+=dir;
            if(index==0||index==numRows-1)  dir=-dir;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<array.length;i++){
            sb.append(array[i].toString());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("AB",1));
        System.out.println(convert("PAYPALISHIRING",3));
    }
}
