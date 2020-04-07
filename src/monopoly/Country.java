package monopoly;
import java.awt.Color;
import java.io.Serializable;
import java.util.*;

public class Country extends Location implements Serializable
{
    private int Cost;
    private int Rent;
    private boolean Bought =false;
    private int nHotels ;
    private int nHouses ;
    private String Owner;
    private int cIndex;
    private boolean setComplete;
    private int listIndex ; 

    public int getcIndex() {
        return cIndex;
    }
    public int getListIndex() {
        return listIndex;
    }
    public void setListIndex(int x)
    {
        this.listIndex =  x ; 
    }
    public boolean isSetComplete() {
        return setComplete;
    }

    public void setSetComplete(boolean setComplete) {
        this.setComplete = setComplete;
    }
    
    public Country(String Name, int Cost, int Rent,int cind,Color c)
    {
        super(Name,c);
        this.Cost = Cost;
        this.Rent = Rent;
        this.cIndex=cind;
        Owner = "None";
        nHotels=0;
    }
    
    public int getCost(){return Cost;}

    public void setCost(int Cost){this.Cost = Cost;}

    public int getRent(){return Rent;}
 
    public void setRent(int Rent){this.Rent = Rent;}

    public boolean isBought(){return Bought;}
        
    public void setBought(boolean Bought){this.Bought = Bought;}

    public int getnHotels(){return nHotels;}

    public void setnHotels(int nHotels){this.nHotels = nHotels;}

    public int getnHouses(){return nHouses;}
        
    public void setnHouses(int nHouses){this.nHouses = nHouses;}
    
    public String getOwner(){return Owner;}
    
    public void setOwner(String Owner){this.Owner = Owner;}
    public void removeOwner(){this.Owner = "None" ; }
    
    public String toString()
    {
        return
                super.toString()+
                "Cost: $"+Cost+"\n"+
                "Rent: $"+Rent+"\n"+
                "Owner: "+Owner+"\n"+
                "Houses built: "+nHouses+"\n"+
                "Hotels built: "+nHotels;             
    }
}
