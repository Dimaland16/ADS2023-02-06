import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {
    Node head;
    Node tail;
    private int size = 0;

    class Node{
        public E data;
        public Node next;
        public Node prev;

        public Node(E data){
            this.data = data;
        }
    }

    public String toString(){
        Node currNode = this.head;

        StringBuilder string = new StringBuilder("[");

        while (currNode != null){
            string.append(currNode.data);
            currNode = currNode.next;

            if (currNode != null)
                string.append(", ");
        }

        string.append("]");
        return string.toString();
    }

    @Override
    public boolean add(E e) {
        Node newNode = new Node(e);

        if (tail == null)
            head = newNode;
        else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;

        size++;
        return true;
    }

    @Override
    public void addFirst(E e) {
        Node newNode = new Node(e);

        if (head == null)
            tail = newNode;
        else {
            newNode.next = head;
            head.prev = newNode.prev;
        }
        head = newNode;

        size++;
    }

    @Override
    public void addLast(E e) {
        add(e);
    }

    @Override
    public E poll() {
        if (head == null)
            return null;

        Node node = head;
        if (head.next == null){
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }

        size--;
        return node.data;
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        if (tail == null)
            return null;

        Node node = tail;
        if (tail.prev == null){
            tail = null;
            head = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }

        size--;
        return node.data;
    }

    @Override
    public E element() {
        return head.data;
    }

    @Override
    public E getFirst() {
        return element();
    }

    @Override
    public E getLast() {
        return tail.data;
    }

    @Override
    public boolean remove(Object o) {
        if (head == null)
            return false;

        Node currNode = head;
        while (currNode != null){
            if (o.equals(currNode.data)){
                if (currNode == head){
                    pollFirst();
                } else if (currNode == tail) {
                    pollLast();
                } else {
                    currNode.prev.next = currNode.next;
                    currNode.next.prev = currNode.prev;
                }

                size--;
                return true;
            }

            currNode = currNode.next;
        }

        return false;
    }

    public E remove(int index) {
        if (index == 0)
            return pollFirst();
        else if (index == size - 1)
            return pollLast();

        Node currNode = head;

        for (int i = 0; i < index; i++)
            currNode = currNode.next;
        currNode.prev.next = currNode.next;
        currNode.next.prev = currNode.prev;

        size--;
        return currNode.data;
    }

    @Override
    public int size() {
        return size;
    }

}
