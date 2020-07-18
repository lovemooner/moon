package love.moon.j2se.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author dongnan
 * @date 2020/6/18 10:58
 */
public class ReverseList<T> implements Iterable<T> {

    private List<T> list = new ArrayList<>();

    public void add(T t) {
        list.add(t);
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseIterator(list.size());
    }

    class ReverseIterator implements Iterator<T> {
        int index;

        ReverseIterator(int index) {
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public T next() {
            index--;
            return ReverseList.this.list.get(index);
        }
    }

    public static void main(String[] args) {
        ReverseList<String> list = new ReverseList<>();
        list.add("Apple");
        list.add("Orange");
        for (Iterator<String> iterator =list.iterator();iterator.hasNext();) {
            String name = iterator.next();
            System.out.println("Name : " + name);
        }
    }
}
