package jp.co.kokou.linkedlist;

import java.util.Optional;

public interface Iterator<T> {
    Optional<T> getNext();
}
