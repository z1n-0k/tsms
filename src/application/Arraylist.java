package application;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Arraylist<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public Arraylist() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T element) {
        ensureCapacity();
        array[size++] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) array[index];
    }

    public void remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            removeAtIndex(index);
        } else {
            System.out.println("Element not found: " + element);
        }
    }

    private void removeAtIndex(int index) {
        int numToMove = size - index - 1;
        if (numToMove > 0) {
            System.arraycopy(array, index + 1, array, index, numToMove);
        }
        array[--size] = null;
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArraylistIterator();
    }

    private void ensureCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 2);
        }
    }

    private class ArraylistIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) array[currentIndex++];
        }
    }
    public void addAll(Arraylist<T> otherList) {
        for (T element : otherList) {
            add(element);
        }
    }
}
