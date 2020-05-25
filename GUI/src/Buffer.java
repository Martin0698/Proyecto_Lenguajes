


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Queue;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JProgressBar;

public class Buffer<E> {
    
<<<<<<< HEAD
    private Queue<String> buffer;
=======
    private Queue<E> buffer;
>>>>>>> master
    private int MAX_SIZE;
    private int elements;
    DefaultTableModel productions;
    JProgressBar progressbar;
    //private char buffer;
    
<<<<<<< HEAD
    Buffer(int size, DefaultTableModel productions, JProgressBar progressbar) {
        this.buffer = new LinkedList<String>();
=======
    Buffer(int size) {
        this.buffer = new LinkedList<E>();
>>>>>>> master
        this.MAX_SIZE = size;
        this.productions = productions;
        this.progressbar = progressbar;
        this.count = 0;
    }
<<<<<<< HEAD
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
=======
    
    synchronized Object consume() {
        E product;
        
>>>>>>> master
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
        return product;
    }
    
<<<<<<< HEAD
    synchronized void produce(String product) {
        this.elements++;
=======
    synchronized void produce(E product) {
>>>>>>> master
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
