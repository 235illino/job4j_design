package it;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    Object[] objects;
    int position = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(T model) {
        while (position < objects.length && !Objects.isNull(objects[position])) {
            position++;
        }
        objects[position] = model;
    }

    public void set(int index, T model) {
        objects[index] = model;
    }

    public void remove(int index) {
        objects[index] = null;
        System.arraycopy(objects, index + 1, objects, index, objects.length - 1 - index);
    }

    public T get(int index) {
        return (T) objects[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return position < objects.length;
            }

            @Override
            public T next() {
                return (T) objects[position++];
            }
        };

    }
}
