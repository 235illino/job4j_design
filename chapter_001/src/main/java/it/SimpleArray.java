package it;

import java.util.ArrayList;
import java.util.Iterator;
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
        Objects.checkIndex(index, objects.length - 1);
        objects[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, objects.length - 1);
        objects[index] = null;
        System.arraycopy(objects, index + 1, objects, index, objects.length - 1 - index);
        position--;
    }

    public T get(int index) {
        Objects.checkIndex(index, objects.length - 1);
        return (T) objects[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return position >= 0;
            }

            @Override
            public T next() {
                return (T) objects[position++];
            }
        };

    }
}
