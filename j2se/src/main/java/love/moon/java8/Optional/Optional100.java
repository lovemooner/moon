package love.moon.java8.Optional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Optional100 {


    public  List<User.Order> test() {

        Optional<User> user = Optional.of(new User());
        if(user.isPresent()) {
            return user.get().getOrders();
        } else {
            return Collections.emptyList();
        }
    }

    public List<User.Order>  test2(){
        Optional<User> user = Optional.of(new User());
        return user.map(u -> u.getOrders()).orElse(Collections.emptyList());
    }





    class User{
        private List<Order> Orders;

        public List<Order> getOrders() {
            return Orders;
        }

        public void setOrders(List<Order> orders) {
            Orders = orders;
        }

        class Order{

        }
    }



}
