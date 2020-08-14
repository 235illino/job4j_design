package collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info infoAnalize = new Info();
        Map<Integer, String> map = new HashMap<>();
        current.forEach(s -> map.put(s.id, s.name));
        for (User user : previous) {
            if (!map.containsKey(user.id)) {
                infoAnalize.deleted++;
            }
            if (!user.name.equals(map.get(user.id))) {
                infoAnalize.changed++;
            }

        }
        infoAnalize.added = current.size() + infoAnalize.deleted - previous.size();
        return infoAnalize;

    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {

        int added;
        int changed;
        int deleted;

    }

    public boolean changed(User pr, User cur) {
        boolean change = false;
        if (!pr.name.equals(cur.name) && pr.id == cur.id) {
            change = true;
        }
        return change;
    }
}
