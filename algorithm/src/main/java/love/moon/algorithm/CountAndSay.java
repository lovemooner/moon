package love.moon.algorithm;

/**
 * @author : ndong
 * date : 2020/12/20 21:56
 * desc :
 */
public class CountAndSay {

    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }

    public static String countAndSay(int n) {
        String[] r=new String[n];
        r[0]="1";
        for(int i=1;i<n;i++){
            StringBuilder sb=new StringBuilder();
            String s= r[i-1];
            for(int j=0; j<s.length();){
                int count=1;
                char c=s.charAt(j);
                while(++j<s.length()&&s.charAt(j)==c){
                    count++;
                }
                sb.append(count).append(c);
            }
            r[i]=sb.toString();
        }
        return r[n-1];
    }
}
