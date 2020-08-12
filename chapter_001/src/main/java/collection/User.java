package collection;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return children == user.children &&
                name.equals(user.name) &&
                birthday.equals(user.birthday);
    }

    @Override
    public int hashCode() {
        return 37 * children + (name == null ? 0 : name.hashCode()) + (birthday == null ? 0 : birthday.hashCode());
    }


    public static void main(String[] args) {
        User mother = new User("Ann", 2, new GregorianCalendar(10, 07, 89));
        User father = new User("Ann", 2, new GregorianCalendar(10, 07, 89));
        Map<User, String> map = new HashMap<>();
        map.put(mother, "1");
        map.put(father, "2");
        System.out.println(map.toString());
    }

}
