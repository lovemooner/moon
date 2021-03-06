package love.moon.j2se.collection.queue;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author : ndong
 * date : 2020/11/25 18:32
 * desc :
 */
public class PriorityQueue100 {

    @Test
    public void test(){
        Queue<Integer> q = new PriorityQueue<>((n1, n2) -> n1 - n2);
        // 添加3个元素到队列:
        q.offer(1);
        q.offer(10);
        q.offer(10);
        q.offer(2);
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll()); // null,因为队列为空
    }

    @Test
    public void customComparator(){
        Queue<User> q = new PriorityQueue((Comparator<User>) (u1, u2) -> u1.number.compareTo(u2.number));
        // 添加3个元素到队列:
        q.offer(new User("Bob", 1));
        q.offer(new User("Alice", 3));
        q.offer(new User("Boss", 2));
        System.out.println(q.poll()); // Boss/V1
        System.out.println(q.poll()); // Bob/A1
        System.out.println(q.poll()); // Alice/A2
        System.out.println(q.poll()); // null,因为队列为空
    }


     class User{
        public final String name;
        public final Integer number;

        public User(String name, Integer number) {
            this.name = name;
            this.number = number;
        }

        public String toString() {
            return name + "/" + number;
        }
    }
}
