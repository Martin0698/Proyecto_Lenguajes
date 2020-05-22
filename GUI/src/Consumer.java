


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int consumers;
    int ms;
 
    Consumer(Buffer buffer, int consumers,int ms) {
        this.buffer = buffer;
        this.consumers=consumers;
        this.ms=ms;
   
    }
    public int getRandom(int from, int to) {
    if (from < to)
        return from + new Random().nextInt(Math.abs(to - from));
    return from - new Random().nextInt(Math.abs(to - from));
    }   
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        int product;
        
        for(int i=0 ; i<consumers ; i++) {
            product = (Integer)this.buffer.consume();
            //System.out.println("Consumer consumed: " + product);
            Buffer.print("Consumer consumed: " + product);
            
            try {
                Thread.sleep(this.ms);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
