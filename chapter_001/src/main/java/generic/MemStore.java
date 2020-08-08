package generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();


    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rep = indexof(id) != 0;
        if (rep) {
            mem.set(indexof(id), model);
        }
        return rep;
    }

    @Override
    public boolean delete(String id) {
        boolean rep = indexof(id) != 0;
        if (rep) {
            mem.remove(indexof(id));
        }
        return rep;
    }

    @Override
    public T findById(String id) {
        T model = null;
        if (indexof(id) != 0) {
            model = mem.get(indexof(id));
        }

        return model;
    }

    public int indexof(String id) {
        int findId = 0;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                findId = i;
                break;
            }
        }
        return findId;
    }
}
