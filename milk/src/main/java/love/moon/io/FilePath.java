package love.moon.io;

/**
 * Created by lovemooner on 2017/4/27.
 */
public class FilePath {

    public static void main(String[]args){
        FilePath filePath=new FilePath();
        System.out.println(filePath.getClass().getResource("/"));
        System.out.println(filePath.getClass().getResource(""));
        System.out.println(filePath.getClass().getClassLoader().getResource(""));
    }
}
