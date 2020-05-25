


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Producer extends Thread {
    Buffer buffer;
    private int waitMillis;
    private int n;
    private int m;
    String operaciones;
    private volatile boolean Running = true;
    
    Producer(Buffer buffer, int ms, int n, int m, String operaciones) {
        this.buffer = buffer;
        this.waitMillis = ms;
        this.n= n;
        this.m= m;
        this.operaciones = operaciones;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
         
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
            
            operator = operaciones.charAt((int) (Math.random()*(this.operaciones.length())) );
            int Value1 = (int)(Math.random() *difference)+ n;
            int Value2 = (int)(Math.random() *difference)+ n;
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
