/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse;

import java.util.Random;

/**
 *
 * @author student
 */
public class Warehouse 
{
    public static final int WAREHOUSE_SIZE = 10;
    private Product[] temp;
    private int temp_iter = 0;
    private int counter = 0;
    
    private Product products[];

    public Warehouse() 
    {
        this.products = new Product[WAREHOUSE_SIZE];
        for (int i=0; i < WAREHOUSE_SIZE; i++)
            this.products[i] = null;
        
        this.temp = new Product[WAREHOUSE_SIZE];
        for (int i=0; i < WAREHOUSE_SIZE; i++)
            this.temp[i] = null;
        
    }
    
    public synchronized boolean addContainer(Product t)
    {
        for (int i=0; i < WAREHOUSE_SIZE; i++)
        {
            if (this.products[i] == null)
            {
                this.products[i] = t;
                System.out.println("[WAREHOUSE] TAKE: " + Types.getString(t.getType()) + "(" + t.getAmount() + ") on " + i + " spot");
                counter++;
                if (counter == 3)
                {
                    counter = 0;
                    warehouseState();
                }
                return true;
            }
        }
        return false;
    }
    
    public synchronized void warehouseState()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("-------- State of the Warehouse ----\n");
        for (int i=0; i < WAREHOUSE_SIZE; i++)
        {
            sb.append("--(").append(i).append(") ");
            if (this.products[i] == null)
                sb.append("EMPTY");
            else
            {
                sb.append(Types.getString(this.products[i].getType())).append("(").append(this.products[i].getAmount()).append(")");
            }
            sb.append("\n");
        }
        sb.append("---------------------------\n");
        System.out.println(sb.toString());
    }
    

    
    public synchronized boolean giveProd(Product t)
    {
        int toLeft = t.getAmount();
        for (int i=0; i < WAREHOUSE_SIZE; i++)
        {
            if (this.products[i] != null && this.products[i].getType() == t.getType())
            {
                toLeft -= this.products[i].getAmount();
                temp[temp_iter++] = this.products[i];
            }
        }
        boolean result = false;
        if (toLeft <= 0)
        {   // zabieramy!
            int toLeftNo = t.getAmount();
            for (int i=0; i < temp_iter; i++)
            {
                toLeftNo = temp[i].take(toLeftNo);
                if (toLeftNo == 0) break;
            }
            result = true;
            for (int i=0; i < WAREHOUSE_SIZE; i++)
            {
                if (this.products[i] != null && this.products[i].isEmpty())
                    this.products[i] = null;
            }
        }
        for (int i=0; i < temp_iter; i++)
            temp[i] = null;
        temp_iter = 0;
        return result;
    }
}

