package collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> last;

    public void add(T value) {
        ForwardLinked.Node<T> l = last;
        ForwardLinked.Node<T> newOne = new ForwardLinked.Node(l, value, null);
        last = newOne;
        if (l == null) {
            head = newOne;
        } else {
            l.next = newOne;

        }
    }

    public T removeFirst() {
        ForwardLinked.Node<T> f = this.head;
        if (f == null) {
            throw new NoSuchElementException();
        } else {
            T element = f.value;
            ForwardLinked.Node<T> next = f.next;
            f.value = null;
            f.next = null;
            this.head = next;
            return element;
        }
    }

    public T removeLast() {
        ForwardLinked.Node<T> l = this.last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        else {
            T element = l.value;
            ForwardLinked.Node<T> prev = l.prev;
            l.value = null;
            l.prev = null;
            this.last = prev;
            if (prev == null) {
                this.head = null;
            } else {
                prev.next = null;
            }

            return element;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        public Node(Node<T> prev, T value, Node<T> next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }


}
