package jp.co.kokou;

import jp.co.kokou.linkedlist.LinkedList;

public class Main {
    public static void main(String[] args) {
        var list = new LinkedList<Integer>();
        list.append(10).append(20).append(30).append(40);

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
