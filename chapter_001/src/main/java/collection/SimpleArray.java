package collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int position = 0;
    private int modCount = 0;
    public int size = 0;


    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public T get(int index) {
        Objects.checkIndex(index, position);
        return (T) objects[index];
    }

    public void add(T model) {
        if (position == size) {
            extend();
        }
        objects[position] = model;
        position++;
        modCount++;
    }

    public void extend() {
        size = (size + size / 2) + 1;
        objects = Arrays.copyOf(objects, size);
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            private int it = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return it < position;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) objects[it++];
            }
        };
    }
}