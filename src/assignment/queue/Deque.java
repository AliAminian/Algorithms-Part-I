package assignment.queue;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {	
	private Node<Item> head, tail;
	private int size;
	
	private class Node<E> {
		public E item;
		public Node<E> next;
		public Node<E> previous;
	}
	
    public Deque() {
    }
    
    public boolean isEmpty() {
    	return this.size == 0;
    }

    public int size() {
    	return this.size;
    }

    public void addFirst(Item item) {
    	if (item == null) {
    		throw new IllegalArgumentException("null argument");
    	}
    	
    	Node<Item> newHead = new Node<Item>();
    	newHead.item = item;
    	if (this.size == 0) {
    		this.tail = newHead;
    	} else {
    		head.previous = newHead;
    		newHead.next = head;
    		
    	}
    	this.head = newHead;
        size++;
    }

    public void addLast(Item item) {
    	if (item == null) {
    		throw new IllegalArgumentException("null argument");
    	}
    	Node<Item> newTail = new Node<Item>();
    	newTail.item = item;
    	if (this.size == 0) {
    		this.head = newTail;
    		this.tail = newTail;
    	} else {
    		tail.next = newTail;
    		newTail.previous = tail;
    		tail = newTail;
    		
    	}
        size++;
    	
    }

    public Item removeFirst() {
    	if (this.isEmpty()) {
    		throw new NoSuchElementException("deque is empty");
    	}
    	
    	Node<Item> removedItem = this.head;
    	if (this.size == 1) {
    		this.head = null;
    		this.tail = null;
    	} else {
    		Node<Item> second = head.next;
            second.previous = null;
            head.next = null;
            head = second;
    		
    	}
    	size--;
    	return removedItem.item;
    }

    public Item removeLast() {
    	if (this.isEmpty()) {
    		throw new NoSuchElementException("deque is empty");
    	}
    	Node<Item> removedItem = this.tail;
    	if (this.head == this.tail) {
    		this.head = null;
    		this.tail = null;
    	} else {
    		Node<Item> oldLast = tail;
    		tail = oldLast.previous;
    		tail.next = null;
            oldLast.previous = null;
            oldLast = null;
    	}
    	size--;
    	return removedItem.item;
    }

    public Iterator<Item> iterator() {
    	return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
    	Node<Item> current = head;
    	public boolean hasNext() {
            return current != null;
        }
        public Item next() {
        	if (current == null) {
                throw new NoSuchElementException();
            }
        	Item item = current.item;
        	current = current.next;
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
    	Deque<String> deque = new Deque<>();
    	StdOut.println("Deque instance created");
    	
    	deque.addFirst("head");
    	deque.addFirst("hi");
    	deque.addFirst("1");
    	StdOut.println("addFirst is called: 1, hi, head");
    	
    	deque.addLast("hi");
    	deque.addLast("last");
    	deque.addLast("2");
    	StdOut.println("addLast is called: 1, hi, last, 2");
    	
    	StdOut.println("removeFirst is called: " + deque.removeFirst());
    	
    	StdOut.println("removeLast is called: " + deque.removeLast());
    	
    	StdOut.println("size() is called: " + deque.size());

    	StdOut.println("isEmpty() is called: " + deque.isEmpty());
    	
    	
    	StdOut.println("hasNext is called");
    	StdOut.println("next() is called");
    	Iterator<String> itr = deque.iterator();
    	while (itr.hasNext()) {
    		StdOut.println(itr.next());
    	}
    	
    	StdOut.println("remove() is called");
    }

}
