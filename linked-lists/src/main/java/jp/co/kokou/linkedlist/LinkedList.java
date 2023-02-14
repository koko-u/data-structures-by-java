package jp.co.kokou.linkedlist;

import java.util.Optional;

public class LinkedList<T> {
    private Optional<Node> head;
    private Optional<Node> tail;
    private int length;

    public LinkedList() {
        this.head = Optional.empty();
        this.tail = Optional.empty();
        this.length = 0;
    }

    public LinkedList<T> append(T value) {
        var node = new Node(value);
        node.next = this.head;
        this.head = Optional.of(node);
        if (this.tail.isEmpty()) {
            this.tail = Optional.of(node);
        }
        this.length++;
        return this;
    }

    public Iterator<T> getIterator() {
        return new LinkedListIterator(this);
    }

    class Node {
        T value;
        Optional<Node> next;

        public Node(T value) {
            this.value = value;
            this.next = Optional.empty();
        }
    }

    class LinkedListIterator implements Iterator<T> {
        Optional<Node> next;

        public LinkedListIterator(LinkedList<T> it) {
            this.next = it.head;
        }

        @Override
        public Optional<T> getNext() {
            var value = this.next.map(node -> node.value);
            this.next = this.next.flatMap(node -> node.next);
            return value;
        }
    }
}
