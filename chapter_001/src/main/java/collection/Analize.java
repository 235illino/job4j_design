package collection;

import java.util.List;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info infoAnalize = new Info();
        int dif = current.size() - previous.size();
        if (dif > 0) {
            infoAnalize.added = dif;
        } else if (dif < 0) {
            infoAnalize.deleted = Math.abs(dif);
        }
        for (int i = 0; i < previous.size(); i++) {
            for (int j = 0; j < current.size(); j++) {
                if (changed(previous.get(i), current.get(j))) {
                    infoAnalize.changed++;
                }
            }
        }
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

    public boolean changed (User pr, User cur) {
        boolean change = false;
        if (!pr.name.equals(cur.name) && pr.id == cur.id) {
            change = true;
        }
        return change;
    }
}
