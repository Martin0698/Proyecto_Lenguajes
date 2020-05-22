


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
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
    }
   public int getRandom(int from, int to) {
    if (from < to)
        return from + new Random().nextInt(Math.abs(to - from));
    return from - new Random().nextInt(Math.abs(to - from));
    }   
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
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
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
