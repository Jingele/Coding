package GraphTheory;

import Basic.Stack;

/**
 * Created by nemo on 17-7-3.
 */
public class Acyclic {

    private boolean[] marked;
    private boolean[] onStack;
    private int[] adgeTo;

    private Stack<Integer> cycle;

    public Acyclic(Graph G) {
        marked = new boolean[G.V()];
        onStack= new boolean[G.V()];
        adgeTo = new int[G.V()];
        for(int v =0 ; v < G.V(); v++){
            if(!marked[v]){
                dfs(G,v,v);
            }
        }
    }

    public void dfs(Graph G, int c, int p){
        marked[c] = true;
        for(int x : G.adj(c)){
            if(hasCycle()){
                return;
            }
            else if(!marked[x]){
                adgeTo[x] = c;
                dfs(G,x,c);
            }
            else if(x != p){//a cycle is detected!
                cycle = new Stack<>();
                for(int k = x; k != c; k = adgeTo[k]){
                    cycle.push(k);
                }
                cycle.push(c);
            }
        }
    }


    public void dfs(Graph G, int v){
        marked[v] = true;
        onStack[v] = true;
        for(int w : G.adj(v)){
            if(hasCycle()){
                return;
            }
            else if(!marked[w]){
                adgeTo[w] = v;
                dfs(G,w);
            }
            else if(onStack[w]){
                cycle = new Stack<>();
                for(int x = v; x!= w; x=adgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }
}
