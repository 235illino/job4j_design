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
        boolean rep = false;
        for (int i = 0; i < mem.size() - 1; i++) {
            if (mem.get(i).getId().equals(id)) {
                mem.set(i, model);
                rep = true;
                break;
            }
        }
        return rep;
    }

    @Override
    public boolean delete(String id) {
        boolean del = false;
        for (int i = 0; i < mem.size() - 1; i++) {
            if (mem.get(i).getId().equals(id)) {
                mem.remove(i);
                del = true;
                break;
            }
        }
        return del;
    }

    @Override
    public T findById(String id) {
        T model = null;
        for (int i = 0; i < mem.size() - 1; i++) {
            if (mem.get(i).getId().equals(id)) {
                model = mem.get(i);
                break;
            }
        }
        return model;
    }
}
