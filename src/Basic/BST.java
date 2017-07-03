package Basic;

import org.jetbrains.annotations.Contract;

/**
 * Created by nemo on 17-6-26.
 */
public class BST<Key extends Comparable<Key>,Value>{

    private Node root;

    private class Node{
        private Key key;            // key
        private Value val;          // associated value with key
        private Node left, right;   //links to subtree
        private int N;              // #nodes in subtree rooted here

        private byte flag;           // for post_disp:
        // flag = 1;(first visit to get his right children, and keep it in the stack);
        // flag = 2:(second visit: after travel through all his right children, print it, and pop it)

        public Node(Key key, Value val, Node left, Node right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;

            flag = 0;
        }
    }

    public BST() {
        this.root = null;
    }

    public BST(Node root) {
        this.root = root;
    }

    public void insert(Key key , Value val){
        root = insert(root,key,val);
    }
    private Node insert(Node x, Key key, Value val){
        if(x == null){//create a leaf node;
            Node t = new Node(key,val,null,null);
            t.N = 1;
            return t;
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            x.left = insert(x.left,key,val);
        }
        else if(cmp > 0){
            x.right = insert(x.right,key,val);
        }
        else{
            ;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node x){//delete minNode with x as root
        if(x.left == null){// delete x, and replace it with x.right;
            return x.right;
        }
        else {
            x.left = deleteMin(x.left);//delete minNode with x.left as root
            // then x.left points to the successor!
            x.N = size(x.left) + size(x.right) + 1;
            return x;
        }
    }

    public void delete(Key key){
        root=delete(root,key);
    }
    private Node delete(Node x, Key key){
        if(x == null)   return null;

        int cmp = key.compareTo(x.key);
        if(cmp < 0 ) {
            x.left =  delete(x.left,key);
        }
        else{
            if(cmp > 0) {
                x.right = delete(x.right,key);
            }
            else//cmp  == 0
            {
                if(x.left == null) return x.right;
                if(x.right == null) return x.left;
                Node t = x;
                x = min(t.right);
                x.right = deleteMin(t.right);
                x.left = t.left;
            }
        }
        x.N = size(x.left) + size(x.right)+1;
        return x;
    }

    /*-----------------------------------------------------------------------*/

    public int size(){
        return size(root);
    }
    public int size(Node x){
        if(x == null){
            return 0;
        }
        else {
            return x.N;
        }
    }

    public Value get(Key key){
        return get(root,key);
    }
    public Value get(Node x, Key key){
        if(x == null)   return  null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)  return get(x.left,key);
        else if(cmp > 0)  return get(x.right,key);
        else//(cmp == 0)
             return x.val;
    }

    public Value min(){
        return min(root).val;
    }
    private Node min(Node x){
        if(x.left == null) return x;
        else return min(x.left);
    }

    public Value max(){
        return max(root);
    }
    private Value max(Node x){
        if(x.right != null){
            return max(x.right);
        }
        else{
            return x.val;
        }
    }

    public Key floor(Key key){
        Node t= floor(root,key);
        return t.key;
    }
    @Contract("null, _ -> null")
    private Node floor(Node x, Key key){//the largest key in the Basic.BST less than or equal to key
        if(x == null)   return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) { // key is smaller than x.key
            return floor(x.left,key);
        }
        if(cmp > 0){
            Node r = floor(x.right,key);
            return (r == null? x:r);
        }
        return x;
    }

    public Key ceiling(Key key){
        Node x = ceiling(root,key);
        return x.key;
    }
    private Node ceiling(Node x,Key key){//the smallest key in the Basic.BST greater or equal to key
        if(x == null)   return null;
        int cmp = key.compareTo(x.key);
        if(cmp > 0){
            return ceiling(x.right,key);
        }
        else if(cmp < 0){
            Node r = ceiling(x.left,key);
            return (r==null? x:r);
        }
        else{
            return x;
        }
    }


    public Key select(int k){// select elem rank k;
        Node x = select(root,k);
        return x.key;
    }
    private Node select(Node x, int k){
        k-= 1;// the k-th smaller number, is supposed to be bigger than #(k-1) numbers
        if(x == null)   return null;
        int n = size(x.left);
        if( n > k) return select(x.left,k);
        if(n < k) return select(x.right,k-(n+1));
        return x;
    }


    public int rank(Key key){
        return rank(root,key);
    }
    private int rank(Node x, Key key){
        if(x == null) return 0;
        int cmp = key.compareTo(x.key);
        if(cmp > 0) return size(x.left) + 1 + rank(x.right,key);
        else if(cmp < 0) return rank(x.left,key);
        else return size(x.left)+1;//#size(x.left) keys is smaller than the key, and key is #..+1
    }

    public void display(){
        display(root);
        System.out.println("");
        post_disp(root);
    }

    private void display(Node x){
        if(x == null) return;
        display(x.left);
        display(x.right);
        System.out.print(x.key+" ");
    }

    private void level_disp(Node x){
        Queue<Node> q = new Queue<>();
        q.enqueue(x);
        System.out.print("level display:");
        while(!q.isEmpty()){
            Node t = q.dequeue();
            System.out.print(t.key + " ");
            if(t.left != null){
                q.enqueue(t.left);
            }
            if(t.right != null){
                q.enqueue(t.right);
            }
        }
        System.out.println();
    }
    private void fore_disp(Node x){
        Stack<Node> st = new Stack<>();
        Node current = x;
        System.out.print("fore display:");
        while(!st.isEmpty() || current != null){
            while(current!=null){
                System.out.print(current.key);
                st.push(current);
                current = current.left;
            }
            if(!st.isEmpty()){
                current = st.pop().right;
            }
        }
        System.out.println("");
    }
    private void mid_disp(Node x){
        Stack<Node> st = new Stack<>();
        Node current = x;
        System.out.print("middle display:");
        while(current!= null || !st.isEmpty()){
            while(current != null){
                st.push(current);
                current = current.left;
            }
            if(!st.isEmpty()){
                current = st.pop();
                System.out.print(current.key+ " ");
                current = current.right;
            }
        }
        System.out.println("");
    }
    private void post_disp(Node x){
        Stack<Node> st = new Stack<>();
        Node current = x;
        System.out.print("post order display: ");
        while(current!= null || !st.isEmpty()){
            while(current!= null){
                st.push(current);
                current = current.left;
            }
            if(!st.isEmpty()){
                if(st.top().flag != 1){//first visit, to get his right children
                    st.top().flag = 1;
                    current = st.top().right;
                }
                else{//second visit, after his right children has been travelled, pop it.
                    Node t = st.pop();
                    System.out.print(t.key+ " ");
                }
            }
        }
    }


    public static void main(String[] args){
        System.out.println("hello");
        BST<Integer,String> bst = new BST<>();
        bst.insert(3,"c");
        bst.insert(1,"a");
        bst.insert(2,"b");
        bst.insert(4,"d");
        System.out.println("rank: " +bst.rank(2));
        System.out.println("select: "+bst.select(3));
        bst.delete(2);
        bst.display();
    }
}
