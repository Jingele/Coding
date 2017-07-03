package Basic;

import GraphTheory.Digraph;
import GraphTheory.Graph;

/**
 * Created by nemo on 17-7-3.
 */
public class KosarajuSCC {
    private boolean[] marked;
    private int count;
    private int[] id;

    public KosarajuSCC(Digraph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for(int x: order.reversePost()){
            if(!marked[x]){
                dfs(G,x);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v){
        marked[v] = true;
        id[v] = count;
        for(int x : G.adj(v)){
            if(!marked[x]){
                dfs(G,x);
            }
        }
    }
}
