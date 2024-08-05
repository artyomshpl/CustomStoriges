package com.shep.storages;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomHashSet<T> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Node<T>[] table;
    private int size;

    public CustomHashSet() {
        table = new Node[INITIAL_CAPACITY];
        size = 0;
    }

    public void add(T element) {
        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }
        int index = getIndex(element);
        Node<T> newNode = new Node<>(element, null);
        if (table[index] == null) {
            table[index] = newNode;
            size++;
        } else {
            Node<T> current = table[index];
            while (current != null) {
                if (current.value.equals(element)) {
                    return; // Element already exists
                }
                if (current.next == null) {
                    current.next = newNode;
                    size++;
                    break;
                }
                current = current.next;
            }
        }
    }

    public boolean contains(T element) {
        int index = getIndex(element);
        Node<T> current = table[index];
        while (current != null) {
            if (current.value.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void remove(T element) {
        int index = getIndex(element);
        Node<T> current = table[index];
        Node<T> prev = null;
        while (current != null) {
            if (current.value.equals(element)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;
            private Node<T> currentNode = table[currentIndex];

            @Override
            public boolean hasNext() {
                while (currentIndex < table.length && currentNode == null) {
                    currentIndex++;
                    if (currentIndex < table.length) {
                        currentNode = table[currentIndex];
                    }
                }
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = currentNode.value;
                currentNode = currentNode.next;
                if (currentNode == null) {
                    currentIndex++;
                    if (currentIndex < table.length) {
                        currentNode = table[currentIndex];
                    }
                }
                return value;
            }
        };
    }

    private int getIndex(T element) {
        return Math.abs(element.hashCode() % table.length);
    }

    private void resize() {
        int newCapacity = table.length * 2;
        Node<T>[] newTable = new Node[newCapacity];
        for (Node<T> node : table) {
            while (node != null) {
                int index = Math.abs(node.value.hashCode() % newCapacity);
                Node<T> newNode = new Node<>(node.value, null);
                if (newTable[index] == null) {
                    newTable[index] = newNode;
                } else {
                    Node<T> current = newTable[index];
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = newNode;
                }
                node = node.next;
            }
        }
        table = newTable;
    }

    public int size() {
        return size;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
