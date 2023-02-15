package jp.co.kokou;

import jp.co.kokou.linkedlist.LinkedList;

public class Main {
    public static void main(String[] args) {
        var list = new LinkedList<Integer>();
        list.prepend(10).prepend(20).prepend(30).prepend(40);

        var iter = list.getIterator();
        while (true) {
            var value = iter.getNext();
            if (value.isEmpty()) {
                break;
            }
            System.out.println(value.get());
        }
    }
}
