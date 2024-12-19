import java.util.*;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
    private T[] items;
    private int size;
    protected int nextFirst;
    protected int nextLast;
    private static final int INIT_CAPACITY = 8;
    private static final double USAGE_RATIO = 0.25;

    // Constructor for the ArrayDeque
    public ArrayDeque() {
        items = (T[]) new Object[INIT_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    // Helper method to calculate the index before a given index
    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    // Helper method to calculate the index after a given index
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    // Resize the array when it's full or too empty
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int current = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            newItems[i] = items[current];
            current = plusOne(current);
        }
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    // Adds an item to the front of the deque
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    // Adds an item to the back of the deque
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    // Returns true if deque is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of items in the deque
    public int size() {
        return size;
    }

    // Removes and returns the item at the front of the deque, null if empty
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int firstIndex = plusOne(nextFirst);
        T item = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        size--;

        if (items.length >= 16 && size < items.length * USAGE_RATIO) {
            resize(items.length / 2);
        }
        return item;
    }

    // Removes and returns the item at the back of the deque, null if empty
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int lastIndex = minusOne(nextLast);
        T item = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size--;

        if (items.length >= 16 && size < items.length * USAGE_RATIO) {
            resize(items.length / 2);
        }
        return item;
    }

    // Gets the item at the given index, null if out of bounds
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int actualIndex = (nextFirst + 1 + index) % items.length;
        return items[actualIndex];
    }

    // Convert the deque to a List representation
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int current = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            returnList.add(items[current]);
            current = plusOne(current);
        }
        return returnList;
    }

    // Prints the items in the deque from first to last
    public void printDeque() {
        int current = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[current] + " ");
            current = plusOne(current);
        }
        System.out.println();
    }

    // Override the toString() method to display deque elements
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Implement the iterator method for ArrayDeque
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = plusOne(nextFirst);
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No such element.");
                }
                T item = items[currentIndex];
                currentIndex = plusOne(currentIndex);
                count++;
                return item;
            }
        };
    }

    // Override equals method to compare deques
    @Override
    public boolean equals(Object object) {
        if (this == object) return true; // Same instance
        if (object instanceof ArrayDeque o) {
            if (size() != o.size()) {
                return false;
            }
            for (int i = 0; i < size(); i++) {
                if (get(i) != o.get(i)) {
                    return false;
                }
            }


        }
        return true;
    }


        // Test the implementation of ArrayDeque
        public static void main (String[] args){
            Comparator<Double> dc = (o1,o2) -> (int)(o1 - o2);
            ArrayDeque<Double> deck = new ArrayDeque<>();
            deck.addLast(2.3);
            deck.addLast(0.0);
            deck.addFirst(4.7);
            System.out.println(deck);
            System.out.println(deck.toString());

            ArrayDeque<Integer> deque = new ArrayDeque<>();
            deque.addLast(10);
            deque.addLast(20);
            deque.addLast(30);
            deque.addLast(40);
            deque.addLast(50);
            deque.addLast(60);
            deque.addLast(70);
            deque.addLast(80);
            deque.addLast(90);
            deque.addFirst(5);
            deque.removeLast();
            deque.removeFirst();
            deque.printDeque();

            System.out.println("Size: " + deque.size());
            System.out.println("IsEmpty: " + deque.isEmpty());
            System.out.println("Get index 2: " + deque.get(2));

            List<Integer> list = deque.toList();
            System.out.println("toList: " + list);
            System.out.println(deque.max());

        }
    }

