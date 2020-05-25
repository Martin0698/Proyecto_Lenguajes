


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Producer extends Thread {
    Buffer buffer;
<<<<<<< HEAD
    private int waitMillis;
    private int n;
    private int m;
    private volatile boolean Running = true;
    
    Producer(Buffer buffer, int ms, int n, int m) {
        this.buffer = buffer;
        this.waitMillis = ms;
        this.n= n;
        this.m= m;
=======
    int producers;
    int ms;
    int from;
    int to;
    Producer(Buffer buffer,int producers,int ms, int from,int to) {
        this.buffer = buffer;
        this.producers=producers;
        this.ms = ms;
        this.from=from;
        this.to=to;
>>>>>>> master
    }
   public int getRandom(int from, int to) {
    if (from < to)
        return from + new Random().nextInt(Math.abs(to - from));
    return from - new Random().nextInt(Math.abs(to - from));
    }   
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
<<<<<<< HEAD
        
        String products = "+-/*";
        
        //TODO, generate list with the m,n range values.
        
        //TODO Get n m and get the difference;
        int difference = m-n;
      
        
        Random r, rval1, rval2;
        rval1 = new Random(System.currentTimeMillis());
            rval2 = new Random(System.currentTimeMillis());
        char operator;
        String product;
        while(Running) {
            r= new Random(System.currentTimeMillis());
            
            operator = products.charAt((int) (Math.random()*4) );
            int Value1 = (int)(Math.random() *difference)+ n;
            int Value2 = (int)(Math.random() *difference)+ n;
            product="("+operator+" "+ Value1 +" " +Value2+ ")";
            
            this.buffer.produce(product);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer " + this.getId() + " produced: " + product);
            
            try {
                Thread.sleep(this.waitMillis);
=======
        //String products = "AEIOU";
        //Random r = new Random(System.currentTimeMillis());
        //char product;
        
        for(int i=0 ; i<producers ; i++) {
            //product = products.charAt(r.nextInt(5));
            //this.buffer.produce(product);
            int number = getRandom(from,to);
            this.buffer.produce(number);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer produced: " + number);
            
            try {
                Thread.sleep(this.ms);
>>>>>>> master
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
