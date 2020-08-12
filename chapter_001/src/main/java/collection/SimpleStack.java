package collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.removeLast();
    }

    public void push(T value) {
        linked.add(value);
    }

    public boolean isEmpty() {
        return linked.getPos() == 0;
    }
}
