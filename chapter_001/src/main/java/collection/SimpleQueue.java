package collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int count = 0;

    public T poll() {
        T value = null;
        for (int i = 0; i < count; i++) {
            out.push(in.pop());
        }
        value = out.pop();
        for (int i = 0; i < count - 1; i++) {
            in.push(out.pop());
        }
        count--;
        return value;
    }

    public void push(T value) {
        in.push(value);
        count++;
    }
}