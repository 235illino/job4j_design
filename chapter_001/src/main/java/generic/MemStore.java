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
        int ind = indexof(id);
        boolean rep = ind != 0;
        if (rep) {
            mem.set(ind, model);
        }
        return rep;
    }

    @Override
    public boolean delete(String id) {
        int ind = indexof(id);
        boolean rep = ind != 0;
        if (rep) {
            mem.remove(ind);
        }
        return rep;
    }

    @Override
    public T findById(String id) {
        int ind = indexof(id);
        T model = null;
        if (ind != 0) {
            model = mem.get(ind);
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
