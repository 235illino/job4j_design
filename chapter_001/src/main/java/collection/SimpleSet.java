package collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> array = new SimpleArray<>(10);
    private int position = 0;

    public void add(T t) {
        if (position == 0) {
            array.add(t);
            position++;
        } else {
            for (int i = 0; i <= position; i++) {
                if (!contains(t, array.get(i))) {
                    array.add(t);
                    position++;
                }

            }
        }

    }
    public boolean contains(T t, T i) {
        return Objects.equals(t, i);
    }

    @Override
    public Iterator<T> iterator() {
        return array.iterator();

    }
}
