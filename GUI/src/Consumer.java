


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
                int resultado_scheme = schemesolver(product);
                Buffer.print("Consumer "+ this.getId()  + " consumed: " + product+" result scheme: "+resultado_scheme);
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
                result= valor1 / valor2;
                break;
               
        
        }
          
        return  result;
    }
    
    public void terminate(){
        Running = false;
    }
}
