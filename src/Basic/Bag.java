package Basic;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Created by nemo on 17-7-1.
 */
public class Bag<Item> implements Iterable<Item>{
    Node first;
    int count;
    private class Node{
        Item item;
        Node next;
    }

    public void add(Item item){
        Node old = first;
        first = new Node();
        first.item = item;
        first.next = old;
        count ++;
    }

    public int size(){
        return count++;
    }

    @NotNull
    @Override
    public Iterator<Item> iterator() {
        return new BagIterator();
    }

    public class BagIterator implements Iterator<Item>{
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
