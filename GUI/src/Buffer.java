


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Queue;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;

public class Buffer {
    
    private Queue<String> buffer;
    private int MAX_SIZE;
    private int elements;
    private int consumeCount;
    DefaultTableModel productions;
    JProgressBar progressbar;
    JSpinner jSpinner4;
    //private char buffer;
    
    Buffer(int size, DefaultTableModel productions, JProgressBar progressbar,JSpinner jSpinner4) {
        this.buffer = new LinkedList<String>();
        this.MAX_SIZE = size;
        this.productions = productions;
        this.progressbar = progressbar;
        this.count = 0;
        this.consumeCount = 0;
        this.jSpinner4 = jSpinner4;
    }
    public void updateTable(){
        this.progressbar.setValue(this.elements);
        productions.setRowCount(0);
        for(String s : buffer) { 
            productions.addRow(new Object[]{s.charAt(1), s.charAt(3), s.charAt(5)});
             
        }
    }
    synchronized String consume() {
        String product = "";
        this.elements--;
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
        updateTable();
        this.jSpinner4.setValue(++this.consumeCount);
        return product;
    }
    
    synchronized void produce(String product) {
        this.elements++;
        while(this.buffer.size()>=MAX_SIZE) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer.add(product);
        updateTable();
        notify();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
