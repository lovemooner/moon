package love.moon.design.structural.composite;

/**
 * @auther dongnan
 * @date 2019/11/20 13:49
 * @describe
 */
public class App {

    public static void main(String[] args) {
            System.out.println("Making root entries...");
            Directory rootdir = new Directory("root");
            Directory usrdir = new Directory("usr");
            rootdir.add(usrdir);
            rootdir.printList();

            Directory hanako = new Directory("hanako");
            usrdir.add(hanako);
            hanako.add(new File("memo.tex", 300));
            rootdir.printList();


    }
}
