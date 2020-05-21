


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Producer extends Thread {
    Buffer buffer;
    private int waitMillis;
    private volatile boolean Running = true;
    Producer(Buffer buffer, int ms) {
        this.buffer = buffer;
        this.waitMillis = ms;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        
        String products = "+-/*";
        
        //TODO, generate list with the m,n range values.
        int[] numbers={0,1,2,3,4,5,6,7,8,9};
        //TODO Get n m and get the difference;
        int m = 9;
        int n = 0;
        int difference = m-n;
        Random r, rval1, rval2;
        rval1 = new Random(System.currentTimeMillis());
            rval2 = new Random(System.currentTimeMillis());
        char operator;
        String product;
        while(Running) {
            r= new Random(System.currentTimeMillis());
            
            operator = products.charAt((int) (Math.random()*4) );
            int Value1 = numbers[(int)(Math.random() *difference)+ n];
            int Value2 = numbers[r.nextInt((difference) + n)];
            product="("+operator+" "+ Value1 +" " +Value2+ ")";
            
            this.buffer.produce(product);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer " + this.getId() + " produced: " + product);
            
            try {
                Thread.sleep(this.waitMillis);
            } catch (InterruptedException ex) {
                System.out.println("Stopped thread");
            }
            
        }
    }
    
    public void terminate(){
        System.out.println("Stopping producer...");
        Running = false;
    }
    
}
