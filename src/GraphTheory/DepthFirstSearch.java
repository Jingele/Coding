package GraphTheory;

/**
 * Created by nemo on 17-7-3.
 */
public class DepthFirstSearch {

    private int source;
    private boolean[] marked;
    private int count; // how many vertices connected to source;

    public DepthFirstSearch(Graph G, int s){
        marked = new boolean[G.V()];
        source = s;
        dfs(G,source);
    }

    private void dfs(Graph G, int v){
        marked[v] = true;
        count++;
        for(int x : G.adj(v)){
            if(!marked[x]){
                dfs(G,x);
            }
        }
    }

    public boolean connected(int w){
        return marked[w];
    }

    public int count(){
        return count;
    }

}
