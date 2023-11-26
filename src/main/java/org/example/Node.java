package org.example;

public class Node<T> {
    // Data Stored in each Node of the Linked List
    T data;

    // Pointer to the next node in the Linked List
    Node<T> next;

    // Node class constructor used to initializes the data
    // in each Node
    Node(T data) { this.data = data; }
}
