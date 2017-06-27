import java.util.ArrayList;

/**
 * Created by nemo on 17-6-23.
 */
public class GuessNum {
    private int[][] a;
    private int[][] b;
    private int N;
    private ArrayList<Integer> d;

    public GuessNum(int n) {
        this.N = n;
        a = new int[N+1][N+1];
        b = new int[N+1][N+1];
        d = new ArrayList<>();
    }

    public int solve(){
        for(int L = N-1; L > 0; L--){
            for(int R = L+1; R <= N; R++){
                a[L][R] = Integer.MAX_VALUE;
                for(int k = L; k < R; k++){
                    int tmp = Math.max(a[L][k-1],a[k+1][R])+k;
                    if(tmp < a[L][R]){
                        a[L][R] = tmp;
                        b[L][R] = k;
                    }
                }
            }
        }
        index(1,N);
        System.out.println("the index");
        return a[1][N];
    }

    public void index(int i, int j){
        if(i >= j ){
            return;
        }else{
            int k = b[i][j];
            if(a[i][k-1] >= a[k+1][j]){
                index(i,k-1);
            }else{
                index(k+1,j);
            }
            System.out.print(k+" ");
        }


    }



    public static void main(String[] args){
        GuessNum g = new GuessNum(20);
        System.out.println(g.solve());
    }
}
