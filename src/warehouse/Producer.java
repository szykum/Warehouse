package warehouse;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread
{
    private static final int LIMIT = 100;
    
    private Product randomProd()
    {
        Product t = new Product(Types.randomProd(), (int)(Math.random()*LIMIT)+1);
        return t;
    }
    
    private Warehouse warehouse = null;
    private int index = 0;

    public Producer(Warehouse m, int index) 
    {
        this.warehouse = m;
        this.index = index;
    }
    
    @Override
    public void run() 
    {
        int success = 0;
        int failure = 0;
        while(true)
        {
            Product t = randomProd();
            boolean wynik = this.warehouse.addContainer(t);
            if (wynik)
            {
                success++;
                System.out.println("I'm producer no " + index + " and I give " + Types.getString(t.getType()) + "(" + t.getAmount() + ") to the warehouse");
                failure = 0;
            }
            else
            {
                failure++;
                System.out.println("I'm producer no " + index + " and I could add product to warehouse");
            }
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
