package GraphTheory;

import Basic.Stack;

/**
 * Created by nemo on 17-7-3.
 */
public class Paths {

    private boolean[] marked;
    private int count;//how many connected components ?
    private int id[];
    private int[] adgeTo;

    public Paths(Graph G, int s){
        marked = new boolean[G.V()];
        adgeTo = new int[G.V()];
        this.id = new int[G.V()];
        for(int i = 0; i < G.V(); i++){
            dfs(G,i);//travel through a component;
            count++;
        }
    }

    public void dfs(Graph G, int v){
        marked[v] = true;
        id[v] = count;
        for(int x: G.adj(v)){
            adgeTo[x] = v;
            dfs(G,x);
        }
    }

    public boolean hasPath(int v, int w){
        return id[v]==id[w];
    }

    public Iterable<Integer> pathTo(int s,int v){
        if(!hasPath(s,v)){
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for(int x = v; x != s; x=adgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public int count(){
        return count;
    }
}
