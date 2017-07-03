package GraphTheory;

import Basic.Bag;

/**
 * Created by nemo on 17-7-3.
 */
public class Digraph {
    private int V;
    private int E;
    private Bag<Integer>[]  adj;

    public Digraph(int v){
        this.V = v;
        this.E = 0;
        this.adj = (Bag<Integer>[]) new Bag[v];
        for(int w = 0; w < V; w++){
            adj[w] =  new Bag<>();
        }
    }

    public void addEdge(int w, int v){
        adj[w].add(v);//w->v
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj(v);
    }

    public int V(){
        return this.V;
    }

    public int E() {
        return E;
    }

    public Digraph reverse(){
        Digraph G = new Digraph(V);
        for(int v = 0; v < V; v++){
            for(int x: adj[v]){//v->x
                G.addEdge(x,v);//x->v!!!
            }
        }
        return G;
    }
}
