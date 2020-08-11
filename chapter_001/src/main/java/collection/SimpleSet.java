package collection;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T>{
    private SimpleArray<T> array = new SimpleArray<>(10);
    private int position = 0;

    public void add(T t) {
        if (position == 0) {
            array.add(t);
            position++;
        } else {
            for (int i = 0; i <= position; i++) {
                if (!t.equals(array.get(i))) {
                    array.add(t);
                    position++;
                }

            }
        }

    }

    @Override
    public Iterator<T> iterator() {
        return array.iterator();

    }
}
