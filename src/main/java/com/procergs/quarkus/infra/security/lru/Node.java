package com.procergs.quarkus.infra.security.lru;

@SuppressWarnings("rawtypes")
public class Node<T> implements LinkedListNode<T> {
    private T value;
    private DoublyLinkedList<T> list;
    private LinkedListNode next;
    private LinkedListNode prev;

    @SuppressWarnings("unchecked")
    public Node(T value, LinkedListNode<T> next, DoublyLinkedList<T> list) {
        this.value = value;
        this.next = next;
        this.setPrev(next.getPrev());
        this.prev.setNext(this);
        this.next.setPrev(this);
        this.list = list;
    }

    @Override
    public boolean hasElement() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public T getElement() {
        return value;
    }


    @SuppressWarnings("unchecked")
    public void detach() {
        this.prev.setNext(this.getNext());
        this.next.setPrev(this.getPrev());
    }

    @Override
    public DoublyLinkedList<T> getListReference() {
        return this.list;
    }

    @Override
    public LinkedListNode<T> setPrev(LinkedListNode<T> prev) {
        this.prev = prev;
        return this;
    }

    @Override
    public LinkedListNode<T> setNext(LinkedListNode<T> next) {
        this.next = next;
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public LinkedListNode<T> getPrev() {
        return this.prev;
    }

    @SuppressWarnings("unchecked")
    @Override
    public LinkedListNode<T> getNext() {
        return this.next;
    }

    @Override
    public LinkedListNode<T> search(T value) {
        return this.getElement() == value ? this : this.getNext().search(value);
    }
}
