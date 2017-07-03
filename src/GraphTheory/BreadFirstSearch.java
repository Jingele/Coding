package GraphTheory;

import Basic.Queue;

/**
 * Created by nemo on 17-7-3.
 */
public class BreadFirstSearch {

    private boolean[] marked;
    private int count;
    private Queue<Integer> q;

    public BreadFirstSearch(Graph G, int s){
        bfs(G,s);
    }

    public void bfs(Graph G, int s){
        q = new Queue<>();
        marked = new boolean[G.V()];

        q.enqueue(s);
        while(!q.isEmpty()){
            int current = q.dequeue();
            marked[current] = true;
            for(int x: G.adj(current)){
                if(!marked[x]){
                    q.enqueue(x);
                }
            }
        }
    }
}
