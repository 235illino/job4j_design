package collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinked<E> implements Iterable<E> {
    private SimpleLinked.Node<E> first;
    private SimpleLinked.Node<E> last;
    private int position = 0;
    private int modCount = 0;

    public SimpleLinked() {
    }

    public E get(int index) {
        SimpleLinked.Node x = first;
        Objects.checkIndex(index, position);
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return (E) x.element;

    }

    public void add(E value) {
        SimpleLinked.Node<E> l = last;
        SimpleLinked.Node<E> newOne = new SimpleLinked.Node(l, value);
        last = newOne;
        if (l == null) {
            first = newOne;
        } else {
            l.next = newOne;

        }
        this.position++;
        this.modCount++;

    }



    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private int it = 0;
            private final int expectedModCount = modCount;
            private Node<E> nIt = first;
            @Override
            public boolean hasNext() {
                return it < position;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E rsl = nIt.element;
                nIt = nIt.next;
                it++;
                return  rsl;
            }
        };
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(Node<E> n, E e) {
            next = n;
            element = e;
        }


        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

}