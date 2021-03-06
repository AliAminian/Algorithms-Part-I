package assignment.queue;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] queue;
	private int size;
	
	public RandomizedQueue() {
    	queue = (Item[]) new Object[1];
    }
	
	private void resize(int newSize) {
		Item[] copyQueue = (Item[]) new Object[newSize];
		for (int i = 0; i < size; i++) {
			copyQueue[i] = queue[i];
		}
		queue = copyQueue;
	}

    public boolean isEmpty() {
    	return this.size == 0;
    }

    public int size() {
    	return this.size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        queue[size] = item;
        size++;
        if (size == queue.length) {
        	this.resize(2*size);
        } 
    }

    public Item dequeue() {
    	if (isEmpty()) {
            throw new NoSuchElementException();
        }
    	int randomItem = StdRandom.uniform(this.size);
    	Item removedItem = queue[randomItem];
    	size--;
    	if (randomItem < size) {
    		queue[randomItem] = queue[size];
    	}
    	queue[size] = null;
    	
    	if (size > 0 && size == (queue.length/4)) {
    		this.resize(queue.length/2);
    	}
        return removedItem;
    }

    public Item sample() {
    	if (isEmpty()) {
            throw new NoSuchElementException();
        }
    	return queue[StdRandom.uniform(this.size)];
    }

    private class QueueIterator implements Iterator<Item> {
    	private Item[] output;
        private int counter = 0;

        private QueueIterator() {
            output = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                output[i] = queue[i];
            }
            StdRandom.shuffle(output);
        }

        @Override
        public boolean hasNext() {
            return counter < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return output[counter++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
    
    public Iterator<Item> iterator() {
    	return new QueueIterator();
    }

    public static void main(String[] args) {
    	RandomizedQueue<String> queue = new RandomizedQueue<>();
    	StdOut.println("RandomizedQueue instance created");
    	
    	queue.enqueue("head");
    	queue.enqueue("hi");
    	queue.enqueue("1");
    	StdOut.println("enqueue is called: 1, hi, head");

    	
    	StdOut.println("dequeue is called: " + queue.dequeue());
    	
    	StdOut.println("sample is called: " + queue.sample());
    	
    	StdOut.println("size() is called: " + queue.size());

    	StdOut.println("isEmpty() is called: " + queue.isEmpty());
    	
    	
    	StdOut.println("hasNext is called");
    	StdOut.println("next() is called");
    	Iterator<String> itr = queue.iterator();
    	while (itr.hasNext()) {
    		StdOut.println(itr.next());
    	}
    	
    	StdOut.println("remove() is called");
    }

}
