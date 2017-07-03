package Basic;

import GraphTheory.Digraph;
import GraphTheory.Graph;

import javax.swing.*;

/**
 * Created by nemo on 17-7-3.
 */
public class DepthFirstOrder {

    private boolean[] marked;

    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G){
        marked = new boolean[G.V()];
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();

        for(int i = 0; i<G.V(); i++){
            dfs(G,i);
        }
    }

    private void dfs(Digraph G, int v){
        marked[v] = true;
        pre.enqueue(v);
        for(int x: G.adj(v)){
            dfs(G,x);
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }
}
