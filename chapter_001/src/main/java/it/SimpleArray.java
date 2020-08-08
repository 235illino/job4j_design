package it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int position = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(T model) {
        objects[position] = model;
        position++;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, position);
        objects[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, position);
        objects[index] = null;
        System.arraycopy(objects, index + 1, objects, index, objects.length - 1 - index);
        position--;
    }

    public T get(int index) {
        Objects.checkIndex(index, position);
        return (T) objects[index];
    }

    @Override
    public Iterator<T> iterator() {
        int it = 0;
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return it < position;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) objects[position++];
            }
        };

    }
}
