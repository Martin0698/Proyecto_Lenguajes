


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;

public class Consumer extends Thread {
    Buffer buffer;
    private int waitMillis;
    private volatile boolean Running = true;
    DefaultTableModel consumed;

    Consumer(Buffer buffer, int ms, DefaultTableModel consumed) {
        this.buffer = buffer;
        this.waitMillis = ms;
        this.consumed = consumed;
    }

    @Override
    public void run() {
        log("Running Consumer...");
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
                log("Stopped");
            }
        }
    }

    public int schemesolver(String operacion){


    }

    public void terminate(){
        Running = false;
    }

    private void log (Object obj){

        System.out.println(obj);
    }
}
