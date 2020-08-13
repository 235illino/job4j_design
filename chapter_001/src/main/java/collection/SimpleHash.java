package collection;

import java.util.*;

import static java.util.Objects.hash;

public class SimpleHash<K, V> implements Iterable<K> {
    private final double LOAD_FACTOR = 0.75;
    private Node[] hashMap = new Node[16];
    private int countMod = 0;
    private int entriesCount = 0;

    public boolean insert(K key, V value) {
        boolean insert = false;
        Node<K, V> node = new Node<>(key, value);
        int index = getIndex(key, hashMap);
        if (entriesCount > LOAD_FACTOR * hashMap.length) {
            hashMap = ensureCapa(hashMap);
        }
        if (hashMap[index] == null) {
            hashMap[index] = node;
            insert = true;
            countMod++;
            entriesCount++;
        } else if (Objects.equals(hashMap[index].key, key)) {
            hashMap[index] = node;
            countMod++;
            insert = true;
        }
        return insert;
    }

    public void delete(K key) {
        int index = getIndex(key, hashMap);
        if (Objects.equals(hashMap[index].key, key)) {
            hashMap[index] = null;
            entriesCount--;
            countMod++;
        }
    }

    public V get(K key) {
        int index = getIndex(key, hashMap);
        return Objects.equals(hashMap[index].key, key) ? (V) hashMap[index].value : null;
    }


    public int getIndex(K key, Node[] hashMap) {
        return key.hashCode() & (hashMap.length - 1);
    }


    private Node<K, V>[] ensureCapa(Node[] hashMap) {
        int index;
        Node[] newTab = new Node[hashMap.length * 2];
        for (Node n : hashMap) {
            if (n != null) {
                index = getIndex((K) n.key, newTab);
                newTab[index] = n;
            }
        }
        return newTab;
    }

    @Override
    public Iterator iterator() {
        Iterator<V> iterator = new Iterator<>() {
            private int innerCountMod = countMod;
            private int hasNextCounter = 0;

            @Override
            public boolean hasNext() {
                while (hashMap[hasNextCounter] == null && hasNextCounter < hashMap.length) {
                    hasNextCounter++;
                }
                return hashMap[hasNextCounter] != null;
            }

            @Override
            public V next() {
                if (innerCountMod != countMod) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                V element = (V) hashMap[hasNextCounter].value;
                hasNextCounter++;
                return element;
            }
        };
        return iterator;
    }


    public class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int hashCode() {
            return hash(key, value);
        }
    }
}
