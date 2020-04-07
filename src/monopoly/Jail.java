package monopoly;

import java.io.Serializable;

public class Jail extends Location implements Serializable
{
    boolean hasVisitor;

    public Jail() 
    {
        super("Jail",null);
    }
    
    public boolean isVisited(){return hasVisitor;}
    
    public void setVisitor(boolean hasVisitor){this.hasVisitor = hasVisitor;}
}
