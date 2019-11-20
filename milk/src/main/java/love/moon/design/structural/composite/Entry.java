package love.moon.design.structural.composite;

/**
 * @auther dongnan
 * @date 2019/11/20 13:46
 * @describe
 */
public abstract class Entry {

    public abstract String getName();

    public abstract int getSize();

    public Entry add(Entry entry) {
        throw new RuntimeException();
    }

    public void printList() {
        printList("");
    }

    protected abstract void printList(String prefix);

    public String toString() {
        return getName() + "(" + getSize() + ")";
    }
}
