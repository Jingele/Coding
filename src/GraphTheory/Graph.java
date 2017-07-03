package GraphTheory;

import Basic.Bag;

/**
 * Created by nemo on 17-7-3.
 */
public class Graph {

    private int V;
    private int E;

    private Bag<Integer>[] adj;//adjacent vertices list;

    public Graph(int v){
        this.V = v;
        this.E = v;
        this.adj = (Bag<Integer>[]) new Bag[V];
        for(int i = 0; i < V; i++){
            adj[i] = new Bag<>();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

}
