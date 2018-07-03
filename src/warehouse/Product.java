package warehouse;

public class Product
    {
        private int type;
        private int amount;

        public Product(int type, int amount) 
        {
            this.type = type;
            this.amount = amount;
        }

        public int getType() 
        {
            return type;
        }

        public int getAmount() 
        {
            return amount;
        }
        
        public int take(int no)
        {
            if (this.amount >= no)
            {
                this.amount -= no;
                return 0;
            }
            else
            {
                no -= this.amount;
                this.amount = 0;
                return no;
            }
        }
        
        public boolean isEmpty()
        {
            return this.amount==0;
        }
        
    }