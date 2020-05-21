


import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    private int waitMillis;
    private volatile boolean Running = true;
    
    Consumer(Buffer buffer, int ms) {
        this.buffer = buffer;
        this.waitMillis = ms;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        String product;
        
        while(Running) {
            product = this.buffer.consume();
            //System.out.println("Consumer consumed: " + product);
            
                Buffer.print("Consumer "+ this.getId()  + " consumed: " + product);
                //TODO: solve scheme operations

                try {
                    Thread.sleep(this.waitMillis);
                } catch (InterruptedException ex) {
                    System.out.println("Stopped");
                }
        }
    }
    
    public void terminate(){
        Running = false;
    }
}
