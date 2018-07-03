package warehouse;

import java.util.Random;

public class Types
{
    public static final int BEER = 0;
    public static final int CIGARETTES = 1;
    public static final int VODKA = 2;

    private static final String[] NAMES = { "BEER", "CIGARETTES", "VODKA" };
    private static final int AMOUNT = 3;
    private static Random random = null;

    public static int randomProd()
    {
        if (random == null)
            random = new Random();
        return random.nextInt(3);
    }

    public static String getString(int i)
    {
        return NAMES[i];
    }

}