package jp.co.kokou.linkedlist;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LinkedListTest {
    @Test
    public void emptyList() {
        var empty = new LinkedList<Integer>();
        var iter = empty.getIterator();
        assertThat(iter.getNext()).isEmpty();
    }

    @Test
    public void singleList() {
        var one = new LinkedList<String>();
        one.prepend("ONE");
        var iter = one.getIterator();
        assertThat(iter.getNext()).hasValue("ONE");
        assertThat(iter.getNext()).isEmpty();
    }

    @Test
    public void multipleList() {
        var list = new LinkedList<Character>();
        list.prepend('a').prepend('b').prepend('c');
        var iter = list.getIterator();

        assertThat(list.getLength()).isEqualTo(3);
        assertThat(iter.getNext()).hasValue('c');
        assertThat(iter.getNext()).hasValue('b');
        assertThat(iter.getNext()).hasValue('a');
    }

    @Test
    public void getEmptyListHead() {
        var empty = new LinkedList<Integer>();
        assertThat(empty.getHead()).isEmpty();
    }

    @Test
    public void getHead() {
        var list = new LinkedList<Integer>();
        list.prepend(10).prepend(14).prepend(24);

        assertThat(list.getHead()).hasValue(24);
    }

    @Test
    public void appendValues() {
        var list = new LinkedList<String>();
        list.append("a").append("b").append("c");
        var iter = list.getIterator();

        assertThat(iter.getNext()).hasValue("a");
        assertThat(iter.getNext()).hasValue("b");
        assertThat(iter.getNext()).hasValue("c");
        assertThat(iter.getNext()).isEmpty();
    }

    @Test
    public void removeLastfromEmptyList() {
        var empty = new LinkedList<String>();
        var last = empty.removeLast();
        assertThat(last).isEmpty();
        assertThat(empty.getLength()).isEqualTo(0);
    }

    @Test
    public void removeLastFromOneList() {
        var one = new LinkedList<String>();
        one.append("X");

        var last = one.removeLast();
        assertThat(last).hasValue("X");
        assertThat(one.getLength()).isEqualTo(0);
    }

    @Test
    public void removeLastFromList() {
        var list = new LinkedList<String>();
        list.append("A").append("B").append("C");

        var last = list.removeLast();
        assertThat(last).hasValue("C");
        assertThat(list.getLength()).isEqualTo(2);
    }

    @Test
    public void removeFirstFromEmptyList() {
        var empty = new LinkedList<String>();
        var first = empty.removeFirst();
        assertThat(first).isEmpty();
        assertThat(empty.getLength()).isEqualTo(0);
    }

    @Test
    public void removeFirstFromOne() {
        var one = new LinkedList<String>().append("X");
        var first = one.removeFirst();

        assertThat(first).hasValue("X");
        assertThat(one.getLength()).isEqualTo(0);
    }

    @Test
    public void removeFirstFromList() {
        var list = new LinkedList<String>()
            .append("a")
            .append("b")
            .append("c");
        var first = list.removeFirst();

        assertThat(first).hasValue("a");
        assertThat(list.getLength()).isEqualTo(2);
    }
}
