public interface Deque<T> extends Iterable<T> {
        void addFirst(T item);   // Adds an item to the front of the deque
        void addLast(T item);    // Adds an item to the back of the deque
        boolean isEmpty();       // Returns true if the deque is empty
        int size();              // Returns the number of items in the deque
        T removeFirst();         // Removes and returns the first item, null if empty
        T removeLast();          // Removes and returns the last item, null if empty
        T get(int index);        // Gets the item at a given index, null if out of bounds
        void printDeque();       // Prints the deque from first to last
        java.util.List<T> toList(); // Returns a List representation of the deque
    }


