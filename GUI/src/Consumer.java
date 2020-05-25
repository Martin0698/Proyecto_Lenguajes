


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;

public class Consumer extends Thread {
    Buffer buffer;
<<<<<<< HEAD
    private int waitMillis;
    private volatile boolean Running = true;
    DefaultTableModel consumed;
    
    Consumer(Buffer buffer, int ms, DefaultTableModel consumed) {
        this.buffer = buffer;
        this.waitMillis = ms;
        this.consumed = consumed;
=======
    int consumers;
    int ms;
 
    Consumer(Buffer buffer, int consumers,int ms) {
        this.buffer = buffer;
        this.consumers=consumers;
        this.ms=ms;
   
>>>>>>> master
    }
    public int getRandom(int from, int to) {
    if (from < to)
        return from + new Random().nextInt(Math.abs(to - from));
    return from - new Random().nextInt(Math.abs(to - from));
    }   
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
<<<<<<< HEAD
        String product;
        
        while(Running) {
            product = this.buffer.consume();
            //System.out.println("Consumer consumed: " + product);
                int resultado_scheme = schemesolver(product);
                Buffer.print("Consumer "+ this.getId()  + " consumed: " + product+" result scheme: "+resultado_scheme);
                char operador = product.charAt(1);
                int valor1 = Character.getNumericValue(product.charAt(3));                
                int valor2 = Character.getNumericValue(product.charAt(5));
                Object[]rowData={operador,valor1,valor2,resultado_scheme};

                if(valor2 == 0){
                    rowData[3]="INDT";
                }

                this.consumed.addRow(rowData);
                //TODO: solve scheme operations
                

                try {
                    Thread.sleep(this.waitMillis);
                } catch (InterruptedException ex) {
                    System.out.println("Stopped");
                }
        }
    }
    
    public int schemesolver(String operacion){
        
       char operador = operacion.charAt(1);
        
       int valor1 = Character.getNumericValue(operacion.charAt(3));                
       int valor2 = Character.getNumericValue(operacion.charAt(5));  
        
        int result= 0;
        
        switch(operador){
              case '+':
                result= valor1 + valor2;
                break;
                case '-':
                result= valor1 - valor2;
                break;
                case '*':
                result= valor1 * valor2;
                break;
                case '/':
                    if(valor2 == 0) {
                        result = 0;
                        System.out.println("indeterminado");
                    }

                    else result= valor1 / valor2;
                break;
               
        
=======
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
>>>>>>> master
        }
          
        return  result;
    }
    
    public void terminate(){
        Running = false;
    }
}
