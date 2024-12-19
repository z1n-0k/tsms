package application;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Linkedlist<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    public Linkedlist() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    public void remove(T element) {
        Node<T> current = head;
        Node<T> previous = null;

        while (current != null && !current.data.equals(element)) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            // Element not found
            System.out.println("Element not found: " + element);
            return;
        }

        if (previous == null) {
            // Remove the head
            head = current.next;
        } else {
            // Remove from middle or end
            previous.next = current.next;
        }

        size--;
        System.out.println("Removed element: " + element);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedlistIterator();
    }

    private class LinkedlistIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // Example usage
    public static void main(String[] args) {
        Linkedlist<String> stringList = new Linkedlist<>();
        stringList.add("Apple");
        stringList.add("Banana");
        stringList.add("Orange");

        System.out.println("String List Size: " + stringList.size());
        System.out.println("Elements:");

        for (String element : stringList) {
            System.out.println(element);
        }

        Linkedlist<Integer> intList = new Linkedlist<>();
        intList.add(42);
        intList.add(87);
        intList.add(123);

        System.out.println("\nInteger List Size: " + intList.size());
        System.out.println("Elements:");

        for (int element : intList) {
            System.out.println(element);
        }
    }

    public int indexOf(T element) {
        int index = 0;
        Node<T> current = head;

        while (current != null) {
            if (current.data.equals(element)) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1; // Element not found
    }

	public void addAll(Linkedlist<T> otherList) {
        for (T element : otherList) {
            add(element);
        }
    }
}