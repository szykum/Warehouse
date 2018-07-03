package warehouse;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Project 
{
    private static final int PRODUCER_NO = 3;
    private static final int CUSTOMER_NO = 1;
    
    public static void main(String[] args) 
    {
        Warehouse m = new Warehouse();
        Producer[] producers = new Producer[PRODUCER_NO];
        Client[] customers = new Client[CUSTOMER_NO];
        for (int i=0; i < PRODUCER_NO; i++)
        {
            producers[i] = new Producer(m, i+1);
            producers[i].start();
        }
        for (int i=0; i < CUSTOMER_NO; i++)
        {
            customers[i] = new Client(m, i+1);
            customers[i].start();
        }
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null,ex);
        }
        
    }
}
