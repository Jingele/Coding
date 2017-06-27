import java.util.Iterator;

/**
 * Created by nemo on 17-6-23.
 */
public class Queue<Item> implements Iterable<Item> {

    private class Node{
        Item item;
        Node next;
    }

    Node first;
    Node last;
    int size;

    public Queue(){
        this.first = null;
        this.last = first;
        this.size = 0;
    }

    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if(isEmpty()){
            last = null;
        }
        else{
            ;
        }
        this.size--;
        return item;
    }

    public void enqueue(Item it){
        Node lst = last;
        last = new Node();
        last.item = it;
        last.next = null;
        if(isEmpty()){
            first = last;
        }
        else{
            lst.next = last;
        }
        this.size++;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    public class QueueIterator implements Iterator<Item>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }
        public void remove(){

        }

        @Override
        public Item next() {
            Item it = current.item;
            current = current.next;
            return it;
        }
    }

    public static void main(String[] args){
        System.out.println("hello, queue");
        Queue<Integer> q = new Queue<>();
        for(int i =0; i < 10; i++){
            q.enqueue(i);
            System.out.print(i+" ");
        }
        System.out.println();
        for(Integer x: q){
            System.out.print(x+" ");
        }
        System.out.println();
    }

}
