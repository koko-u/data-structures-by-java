package jp.co.kokou.linkedlist;

import lombok.Getter;

import java.util.Optional;

public class LinkedList<T> {
    private Optional<Node> head;
    private Optional<Node> tail;

    @Getter
    private int length;

    /**
     * コンストラクタです。空のLinkedListを作成します。
     */
    public LinkedList() {
        this.head = Optional.empty();
        this.tail = Optional.empty();
        this.length = 0;
    }

    /**
     * リストの先頭にアイテムを追加します
     * <p>
     * [input]
     * this: o -- o -- o
     * value: x
     * [output]
     * x -- o -- o -- o
     *
     * @param value 追加するアイテム
     * @return 追加された結果のリスト
     */
    public LinkedList<T> prepend(T value) {
        var node = new Node(value);
        node.next = this.head;
        this.head = Optional.of(node);
        if (this.tail.isEmpty()) {
            this.tail = Optional.of(node);
        }
        this.length++;
        return this;
    }

    /**
     * リストの末尾にアイテムを追加します
     * <p>
     * [input]
     * this: o -- o -- o
     * value: x
     * [output]
     * o -- o -- o -- x
     *
     * @param value 追加するアイテム
     * @return 追加された結果のリスト
     */
    public LinkedList<T> append(T value) {
        var node = new Node(value);
        if (this.tail.isEmpty()) {
            this.head = Optional.of(node);
            this.tail = Optional.of(node);
        } else {
            this.tail.ifPresent(t -> t.next = Optional.of(node));
            this.tail = Optional.of(node);
        }
        this.length++;
        return this;
    }

    /**
     * 先頭の要素を取得します
     *
     * @return 先頭の値。空のリストの場合は Optional.empty()
     */
    public Optional<T> getHead() {
        return this.head.map(node -> node.value);
    }

    /**
     * 末尾の要素を取得します
     *
     * @return 末尾の値。空のリストの場合は Optional.empty()
     */
    public Optional<T> getTail() {
        return this.tail.map(node -> node.value);
    }

    /**
     * 末尾の要素を削除します
     *
     * @return 削除した末尾の要素の値。空のリストの場合は Optional.empty()
     */
    public Optional<T> removeLast() {
        if (this.getLength() == 0) {
            return Optional.empty();
        }
        if (this.getLength() == 1) {
            var value = this.tail.map(node -> node.value);
            this.head = Optional.empty();
            this.tail = Optional.empty();
            this.length = 0;
            return value;
        }

        //      head                 tail
        //       |                    |
        //       <> --- <> --- <> --- <>
        //   |   |
        //  pre  current
        Optional<Node> previous = Optional.empty();
        Optional<Node> current = this.head;
        while (true) {
            var next = current.flatMap(node -> node.next);
            if (next.isEmpty()) {
                break;
            }
            current = next;
            previous = current;
        }
        //      head                 tail
        //       |                    |
        //       <> --- <> --- <> --- <>
        //                     |      |
        //                    pre   current
        this.tail = previous;
        previous.ifPresent(node -> node.next = Optional.empty());

        this.length--;

        return current.map(node -> node.value);
    }

    /**
     * 先頭の要素を削除します
     *
     * @return 削除した先頭の要素の値。空のリストの場合は Optional.empty()
     */
    public Optional<T> removeFirst() {
        if (this.getLength() == 0) {
            return Optional.empty();
        }
        if (this.getLength() == 1) {
            var value = this.head.map(node -> node.value);
            this.head = Optional.empty();
            this.tail = Optional.empty();
            this.length = 0;
            return value;
        }

        var value = this.head.map(node -> node.value);
        this.head = this.head.flatMap(node -> node.next);
        this.length--;
        return value;
    }

    /**
     * 指定したインデックスの値を取得する
     *
     * @param index インデックス
     * @return 該当のインデックスに相当する値
     */
    public Optional<T> get(int index) {
        if (index < 0) {
            return Optional.empty();
        }
        
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
