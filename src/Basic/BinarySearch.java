package Basic;

/**
 * Created by nemo on 17-6-27.
 */
public class BinarySearch<Value extends Comparable<Value>> {
    private Value[] val;

    public BinarySearch(Value[] val) {
        this.val = val;
    }

    public int search(Value v){
        return search(this.val,0,this.val.length-1,v);
    }
    /*
        recursive method
     */
    public int search(Value[] a, int low, int high,Value v){
        if(low > high){
            return -1;
        }
        else{
            int mid = low + (high - low)/2;
            int cmp = v.compareTo(a[mid]);
            if(cmp < 0){
                return search(a,low,mid-1,v);
            }
            else if(cmp > 0){
                return search(a,mid+1,high,v);
            }
            else{
                return mid;
            }
        }
    }

    /*
        Elementary method.
     */
    public int run(Value v){
        int low = 0,
                high = val.length-1;
        while(low <= high){
            int mid = low + (high-low)/2;
            int cmp = v.compareTo(val[mid]);
            if(cmp < 0){// v < val[mid]
                high = mid -1;
            }
            else if(cmp > 0){// v > val[mid]
                low = mid+1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int N = 9;
        Integer[] a = new Integer[10];
        for(int i = 0; i < N; i++ ){
            a[i] = i;
        }
        a[1] = 0;
        BinarySearch<Integer> bs = new BinarySearch<>(a);
        System.out.print(bs.run(6));
    }


}
