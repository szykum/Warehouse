package warehouse;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Thread
{
    private static final int LIMIT = 20;
    
    private Product randomProd()
    {
        Product t = new Product(Types.randomProd(), (int)(Math.random()*LIMIT)+1);
        return t;
    }
    
    private Warehouse warehouse = null;
    private int index = 0;

    public Client(Warehouse m, int index) 
    {
        this.warehouse = m;
        this.index = index;
    }
    
    @Override
    public void run() 
    {
        while(true)
        {
            Product t = randomProd();
            boolean result = this.warehouse.giveProd(t);
            if (result)
            {
                System.out.println("I'm costument no " + index + " and take " + Types.getString(t.getType()) + "(" + t.getAmount() + ") from warehouse");
            }
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
