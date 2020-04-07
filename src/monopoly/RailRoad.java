package monopoly;

import java.awt.Color;
import java.io.Serializable;

public class RailRoad extends Location implements Serializable
{  
    private int Cost = 200;
    private int Rent;
    private String Owner = "None";
    
    
    public RailRoad(String Name,Color c) 
    {
        super(Name,c);
    }
    
    public void setRent(int Rent){this.Rent = Rent;}

    private boolean Bought =false;

    public int getCost(){return Cost;}

    public int getRent(){return Rent;}
    
    public boolean isBought(){return Bought;}
    
    public void setBought(boolean b){ this.Bought = b;}
    
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
