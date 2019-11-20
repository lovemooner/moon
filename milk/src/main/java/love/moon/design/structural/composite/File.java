package love.moon.design.structural.composite;

/**
 * @auther dongnan
 * @date 2019/11/20 13:47
 * @describe
 */
public class File extends Entry {

    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix + '/' + this);
    }

}
