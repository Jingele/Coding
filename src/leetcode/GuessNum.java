package leetcode;

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

    public static int cost(int N){
        // 0~N ?
        //k ~ [i,i+step-1];
        //i ~ [0,N+1-step];
        //step ~[0,N+1];
        //dp[i][j] =  Min {Max(dp[i][k-1]+k,k+dp[k+1][j])} for k = i+1,...j-1
        //dp[i][i] = 1;
        int[][] dp = new int[N+1][N+1];
        for(int i =0; i< (N+1); i++){
            dp[i][i] = i;
        }
        for(int step = 1; step < N; step+=1){
            for(int i = 0; (i+step)< N; i+=step){
                int min =Integer.MAX_VALUE;
                for(int k = i;k < i+ step;k++){
                    int L = 0,
                            R =0;
                    if(k-1 >= 0){
                        L = dp[i][k-1];
                    }
                    if(k+1 <= N){
                        R = dp[k+1][i+step-1];
                    }
                    min = Math.min(min,Math.max(L+k,k+R));
                    System.out.print(min+"---");
                }
                dp[i][i+step-1] = min;
                display(dp,N);
            }
        }
        return dp[0][N];
    }

    public static void display(int[][] s,int N ){
        for(int i =0; i <= N; i++){
            for(int j =0; j<= N;j++){
                System.out.print(s[i][j] +" ");
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
    public static void main(String[] args){
        GuessNum g = new GuessNum(2);
//        System.out.println(g.solve());
        System.out.println(cost(5));
    }
}
