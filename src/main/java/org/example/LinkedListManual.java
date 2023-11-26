package org.example;

public class LinkedListManual<T> {
    // Points to the head of the Linked
    // List i.e the first element
    Node<T> head = null;
    int size = 0;

    // Addition of elements to the tail of the Linked List
    public void add(T element)
    {
        // Checks whether the head is created else creates a
        // new one
        if (head == null) {
            head = new Node<>(element);
            size++;
            return;
        }

        // The Node which needs to be added at
        // the tail of the Linked List
        Node<T> add = new Node<>(element);

        // Storing the instance of the
        // head pointer
        Node<T> temp = head;

        // The while loop takes us to the tail of the Linked
        // List
        while (temp.next != null) {
            temp = temp.next;
        }

        // New Node is added at the tail of
        // the Linked List
        temp.next = add;

        // Size of the Linked List is incremented as
        // the elements are added
        size++;
    }

    // Searches the Linked List for the given element and
    // returns it's particular index if found else returns
    // -1
    public int search(T element)
    {

        if (head == null) {
            return -1;
        }

        int index = 0;
        Node<T> temp = head;

        // While loop is used to search the entire Linked
        // List starting from the tail
        while (temp != null) {

            // Returns the index of that particular element,
            // if found.
            if (temp.data == element) {
                return index;
            }

            // Gradually increases index while
            // traversing through the Linked List
            index++;
            temp = temp.next;
        }

        // Returns -1 if the element is not found
        return -1;
    }
}
