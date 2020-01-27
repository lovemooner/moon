package love.moon.oo;

/**
 * Author: lovemooner
 * Date: 2017/5/12 15:28
 */
public class Family {
    private House house;

    public Family() {
        house = new House();
        house.setType(1);
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public static void test(City c){
        c.getHouse().setType(3);
    }

    public static void main(String[] args) {
        House h = new House();
        h.setType(1);
        Family f = new Family();
        f.setHouse(h);
        City c = new City();
        c.setHouse(h);
        h.setType(2);
        System.out.println(c.getHouse().getType());
        System.out.println(f.getHouse().getType());
        test(c);
        System.out.println(c.getHouse().getType());
        System.out.println(f.getHouse().getType());
    }
}
