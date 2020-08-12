package collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<K>{
    private int size;
    private int DEFAULT_CAPACITY = 16;
    private SimpleEntry<K, V>[] values = new SimpleEntry[DEFAULT_CAPACITY];
    private int countMod = 0;


    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (values[i] != null) {
                if (values[i].getKey().equals(key)) {
                    return values[i].getValue();
                }
            }
        }
        return null;
    }

    public void insert(K key, V value) {
        boolean insert = true;
        for (int i = 0; i < size; i++) {
            if (values[i].getKey().equals(key)) {
                values[i].setValue(value);
                insert = false;
            }
        }
        if (insert) {
            ensureCapa();
            values[size++] = new SimpleEntry<K, V>(key, value);
        }
        countMod++;
    }

    private void ensureCapa() {
        if (size == values.length) {
            int newSize = values.length * 2;
            values = Arrays.copyOf(values, newSize);
        }
    }



    public void delete(K key) {
        for (int i = 0; i < size; i++) {
            if (values[i].getKey().equals(key)) {
                values[i] = null;
                size--;
                condenseArray(i);
            }
        }
        countMod++;
    }

    private void condenseArray(int start) {
        for (int i = start; i < size; i++) {
            values[i] = values[i + 1];
        }
    }


    @Override
    public Iterator iterator() {
        Iterator<V> iterator = new Iterator<>() {
            private int innerCountMod = countMod;
            private  int hasNextCounter = 0;

            @Override
            public boolean hasNext() {
                while (values[hasNextCounter] == null && hasNextCounter < values.length - 1) {
                    hasNextCounter++;
                }
                return values[hasNextCounter] != null;
            }

            @Override
            public V next() {
                if (innerCountMod != countMod) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                V element = (V) values[hasNextCounter].getValue();
                hasNextCounter++;
                return element;
            }
        };
        return iterator;
    }
}
