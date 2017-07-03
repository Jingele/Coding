package JavaCoding;

import java.lang.Thread;
/**
 * Created by nemo on 17-6-30.
 */
public class ThreadABC implements Runnable {
    private  String name;
    private Object prev;
    private Object self;

    public ThreadABC(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }


    @Override
    public void run() {
        int count  = 10;
        while(count > 0){
            synchronized (prev){
                synchronized (self){
                    System.out.print(name);
                    count --;
                    self.notify();
                }
                try{
                    prev.wait();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        ThreadABC pa = new ThreadABC("a",c,a);
        ThreadABC pb = new ThreadABC("b",a,b);
        ThreadABC pc = new ThreadABC("c",b,c);

        new Thread(pa).start();
        Thread.sleep(100);
        new Thread(pb).start();
        Thread.sleep(100);
        new Thread(pc).start();
        Thread.sleep(100);
    }
}
