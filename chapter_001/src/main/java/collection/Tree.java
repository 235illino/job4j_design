package collection;

import java.util.*;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parentN = findBy(parent);
        if (parentN.isPresent()) {
            Optional<Node<E>> childN = findBy(child);
            if (childN.isEmpty()) {
                parentN.get().getChildren().add(new Node<>(child));
                rsl = true;
            }
        }
        return rsl;
    }


    public boolean isBinary() {
        boolean isB = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.getChildren().size() > 2) {
                isB = false;
                break;
            }
        }
        return isB;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.getValue().equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }
}
