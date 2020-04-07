package monopoly;
import java.util.*;

public class Die 
{
    public int Roll() 
    {
        Random r = new Random();
        int n1 = r.nextInt(6)+1;
        int n2 = r.nextInt(6)+1;
        return n1+n2;
    }
}

