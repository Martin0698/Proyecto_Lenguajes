


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Queue;
import java.util.LinkedList;

public class Buffer {
    
    private Queue<String> buffer;
    private int MAX_SIZE;
    //private char buffer;
    
    Buffer(int size) {
        this.buffer = new LinkedList<String>();
        this.MAX_SIZE = size;
    }
    
    synchronized String consume() {
        String product = "";
        
        while(this.buffer.isEmpty()) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer.poll();
        //this.buffer = 0;
        notify();
        
        return product;
    }
    
    synchronized void produce(String product) {
        while(this.buffer.size()>=MAX_SIZE) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer.add(product);
    
        notify();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
