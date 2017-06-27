import java.util.Iterator;

/**
 * Created by nemo on 17-6-23.
 */
public class Stack<Item> implements Iterable<Item> {

    private class Node{
        Item item;
        Node next;
    }

    private Node top;
    private int size;

    public Stack(){
        top = null;
        size = 0;
    }

    public void push(Item item){
        Node t = new Node();
        t.item = item;
        t.next = top;
        this.top = t;
        this.size ++;
    }

    public Item pop(){
        if(isEmpty()){
            return null;
        }
        Node t = top;
        top = top.next;
        Item r = t.item;
        t = null;
        this.size--;
        return r;
    }

    public Item top(){
        return top.item;
    }

    public boolean isEmpty(){
        return top == null;
    }

    public int size(){
        return size;
    }


    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    public class StackIterator implements Iterator<Item> {

        private Node current = top;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item it = current.item;
            current = current.next;
            return it;
        }
    }

    public static void main(String[] args){
        System.out.println("hello, world");
        Stack<Integer> s = new Stack<>();
        for(int i = 10; i > 0; i--){
            s.push(i);
            System.out.print(i+" ");
        }
        System.out.println();
        for(Integer x: s){
            System.out.print(x+" ");
        }

        for(Integer x: s){
            System.out.print(x+" ");
        }
    }

}
