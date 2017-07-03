package Basic;

import java.util.Stack;

/**
 * Created by nemo on 17-6-27.
 */
public class SysConvert {// 数据数conversion of number system

    public String convert(long num, int N){
        if(N == 0){
            return null;
        }
        Stack<String> st = new Stack<>();
        while(num != 0){
            long t = num % N;
            String s = String.valueOf(t);
            st.push(s);
            num /= N;
        }
        String result="";
        while(!st.isEmpty()){
            result+=st.pop();
        }
        return result;
    }


    public String bin(long num){// by right shift bits;
        byte x = 1;
        Stack<String> st = new Stack<>();
        while(num > 0){
            x = (byte) (0x01 & num);
            num = num>>1;
            st.push(String.valueOf(x));
        }
        String r = "";
        while(!st.isEmpty()){
            r+= st.pop();
        }
        return r;
    }

    public static void main(String[] args){
        long a = 8967;
        SysConvert s = new SysConvert();
        String r = s.convert(a,2);
        String t = s.bin(a);
        System.out.println(r);
        System.out.println(t);
    }
}
