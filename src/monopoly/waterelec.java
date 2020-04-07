package monopoly;

import java.awt.Color;
import java.io.Serializable;

public class waterelec extends Location implements Serializable
{
    private int Cost = 150;
    private boolean Bought = false;
    private String Owner = "None";
    
    public waterelec(String Name,Color c)  {super(Name,c);}
    
    public boolean isBought(){return Bought;}
    
    public int getCost(){return Cost;}
    
    public void setBought(boolean Bought){this.Bought = Bought;}

    public String getOwner(){return Owner;}
    
    public void setOwner(String Owner){this.Owner = Owner;}
    public void removeOwner(){this.Owner= "None" ;} 
    
    public String toString()
    {
        return
                super.toString()+
                "Cost: $"+Cost+"\n"+
                "Owner: "+Owner+"\n";             
    }
}
