package monopoly;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
public class Player implements Serializable
{
    private String Name;
    private Location currentLocation;
    private ArrayList<Location> OwnedCountries = new ArrayList();
    private int Money = 1500;
    private ImageIcon Img;
    private int x;
    private int y;
    private int Index = 0;
    private boolean Forward = true;
    private boolean isPrisoned = false;
    private boolean freePass = false;
    private boolean cannotCollect = false;
    private boolean hasLost = false;
    public boolean PaidForPrison = false;
    private static ImageIcon house = new ImageIcon("Resources/house2.png");
    private static ImageIcon hotel = new ImageIcon("Resources/hotel.png");
    private char[] myCountries=new char[40];

    private boolean DifferentRent = false;
    
    private Game game ;
    
    
    public Player(String name,String ImgPath, int x , int y){this.Name = name; this.Img = new ImageIcon(ImgPath); this.x = x ; this.y = y;}

    public boolean isDifferentRent(){return DifferentRent;}
    
    public void setDifferentRent(boolean b){ DifferentRent = b;}
            
    public boolean isPrisoned(){return isPrisoned;}
    
    public boolean hasFreePass(){return freePass;}
    
    public void setFreePass(boolean b){freePass = b;}
    
    public boolean CannotCollect() {return cannotCollect;}
       
    public void setCannotCollect(boolean cannotCollect) {this.cannotCollect = cannotCollect;}
        
    public void setPrisoned(boolean b){isPrisoned = b;}
    
    public ImageIcon getImage(){return Img;};
    
    public String getName(){return Name;}
    
    public int getMoney(){return Money;}

    public void addMoney(int Money){this.Money += Money;}
               
    public void deductMoney(int Money){this.Money -= Money;}
    
    public Location getCurrentLocation(){return currentLocation;}

    public void setCurr(Location currentLocation){this.currentLocation = currentLocation;}

    public ArrayList<Location> getOwnedCountries(){return OwnedCountries;}
    
    public void addOwned(Country c){this.OwnedCountries.add(c);}
    
    public void setIndex(int i){Index = i;}
    
    public int getIndex(){return Index;}
    
    public void setDirection(boolean f){Forward = f;}
    
    public boolean isForward(){return Forward;}
    
    public boolean hasLost(){ return hasLost; }
    
    public void setLost(boolean b){hasLost = b;}
    
    public void checker(int a,int b)
    {
      if(myCountries[a]==1&&myCountries[b]==1)
        { 
            for(int i=0;i<OwnedCountries.size();i++)
            {
                if(OwnedCountries.get(i) instanceof Country)
                {
                    Country c = (Country) OwnedCountries.get(i);
                    if(c.getcIndex()==a||c.getcIndex()==b)
                        c.setSetComplete(true);
                }
            }
        }
    }
    
    public void checker(int a, int b ,int c)
    {
        if(myCountries[a]==1&&myCountries[b]==1&&myCountries[c]==1)
            { 
                for(int i=0;i<OwnedCountries.size();i++)
                {
                    if(OwnedCountries.get(i) instanceof Country)
                    {
                        Country d = (Country) OwnedCountries.get(i);
                        if(d.getcIndex()==a||d.getcIndex()==b||d.getcIndex()==c)
                            d.setSetComplete(true);
                    }
                }
            }
    }
    
    public void checkFullSet()
    {
        checker(1,3);
        checker(6,8,9);
        checker(11,13,14); 
        checker(16,18,19);
        checker(21,23,24);
        checker(26,27,29);
        checker(31,32,34);
        checker(37,39);    
    }
            
    public boolean Buy()
    {
        if(currentLocation instanceof Country)
        {
            Country c = (Country) currentLocation;
            if(c.isBought())
            {
                for(int i=0 ; i < OwnedCountries.size() ; i++)
                {
                    if(OwnedCountries.get(i).getName().equals(c.getName()))
                    {
                        JOptionPane.showMessageDialog(null,"You already own this property");
                        return false;
                    }
                }
                
               JOptionPane.showMessageDialog(null,"Country is already owned by "+c.getOwner());
                return false;
            }
            
            else
            {
                if(CanBuy(c.getCost(),false))
                {
                    c.setOwner(this.Name);
                    c.setBought(true); 
                    this.deductMoney(c.getCost());
                    this.OwnedCountries.add(c);
                    myCountries[c.getcIndex()]=1;
                    Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" has bought "+c.getName());
                    checkFullSet();
                    return true;
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"You don't have enough money ");
                     return false;
                }
            }
        }
            
        else if(currentLocation instanceof RailRoad)
        {
            RailRoad r = (RailRoad) currentLocation;
            
            if(r.isBought())
            {
                for(int i=0 ; i < OwnedCountries.size() ; i++)
                {
                    if(OwnedCountries.get(i).getName().equals(r.getName()))
                    {
                        JOptionPane.showMessageDialog(null,"You already own this property.");
                        return false;
                    }
                }
                
                JOptionPane.showMessageDialog(null,"RailRoad is already owned by "+r.getOwner());
                return false;
            }
            
            else
            {
                if(CanBuy(r.getCost(),false))
                {
                    r.setOwner(this.Name);
                    r.setBought(true);
                    this.deductMoney(r.getCost());
                    this.OwnedCountries.add(r);
                    Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" has bought "+r.getName());
                    return true;
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"You don't have enough money ");
                    return false;
                }
            }
        }
        
        else if(currentLocation instanceof waterelec)
        {
            waterelec w = (waterelec) currentLocation;
                
            if(w.isBought())
            {
                for(int i=0 ; i < OwnedCountries.size() ; i++)
                {
                    if(OwnedCountries.get(i).getName().equals(w.getName()))
                    {
                       JOptionPane.showMessageDialog(null,"You already own this property.");
                        return false;
                    }
                }
                
                JOptionPane.showMessageDialog(null,"WaterElec is already owned by "+w.getOwner());
                return false;
            }
            
            else
            {
                if(CanBuy(w.getCost(),false))
                {
                    w.setOwner(this.Name);
                    w.setBought(true);
                    this.deductMoney(w.getCost());
                    this.OwnedCountries.add(w);
                    Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" has bought "+w.getName());
                    return true;
                }
                
                else
                {
                    JOptionPane.showMessageDialog(null,"You don't have enough money ");
                    return false;
                }
                    
            }
        }
       
        else
        {
            JOptionPane.showMessageDialog(null,"This tile cannot be bought");
            return false;
        }
    }
    
    public void draw(Graphics g , Board b)
    {
            g.drawImage(Img.getImage(),x,y,Img.getIconWidth(),Img.getIconHeight(),b);  
            for(int i=0; i<OwnedCountries.size();i++)
            {
                if(OwnedCountries.get(i) instanceof Country)
                {
                    Country c = (Country) OwnedCountries.get(i);
                    if (c.getnHouses()>=0)
                    {
                        if(c.getcIndex()==1)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),560,616,13,15,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),573,616,13,15,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),586,616,13,15,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),599,616,13,15,b);
                            if (c.getnHotels()==1)
                            g.drawImage(hotel.getImage(),560,616,13,15,b);
                        }
                        if(c.getcIndex()==3)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),442,616,13,15,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),455,616,13,15,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),468,616,13,15,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),481,616,13,15,b);
                            if (c.getnHotels()==1)
                            g.drawImage(hotel.getImage(),442,616,13,15,b); 
                        }
                        if(c.getcIndex()==6)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),268,616,13,15,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),281,616,13,15,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),294,616,13,15,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),307,616,13,15,b);
                            if (c.getnHotels()==1)
                             g.drawImage(hotel.getImage(),268,616,13,15,b);    
                        }
                        if(c.getcIndex()==8)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),152,616,13,15,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),165,616,13,15,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),178,616,13,15,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),191,616,13,15,b);
                             if (c.getnHotels()==1)
                                  g.drawImage(hotel.getImage(),152,616,13,15,b);  
                        }
                        if(c.getcIndex()==9)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),93,616,13,15,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),106,616,13,15,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),119,616,13,15,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),132,616,13,15,b);
                             if (c.getnHotels()==1)
                                  g.drawImage(hotel.getImage(),93,616,13,15,b);    
                        }
                        if(c.getcIndex()==11)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),72,558,15,13,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),72,571,15,13,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),72,584,15,13,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),72,597,15,13,b);
                            if (c.getnHotels()==1)
                                g.drawImage(hotel.getImage(),72,558,15,13,b); 
                        }
                        if(c.getcIndex()==13)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),72,442,15,13,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),72,455,15,13,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),72,468,15,13,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),72,481,15,13,b);
                             if (c.getnHotels()==1)
                                  g.drawImage(hotel.getImage(),72,442,15,13,b);
                                 
                        }
                        if(c.getcIndex()==14)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),72,384,15,13,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),72,397,15,13,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),72,410,15,13,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),72,423,15,13,b);
                            if (c.getnHotels()==1)
                                 g.drawImage(hotel.getImage(),72,384,15,13,b);
                                
                        }
                        if(c.getcIndex()==16)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),72,267,15,13,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),72,280,15,13,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),72,293,15,13,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),72,306,15,13,b);
                            if (c.getnHotels()==1)
                                 g.drawImage(hotel.getImage(),72,267,15,13,b); 
                        }
                        if(c.getcIndex()==18)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),72,152,15,13,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),72,165,15,13,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),72,178,15,13,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),72,191,15,13,b);
                            if (c.getnHotels()==1)
                                g.drawImage(hotel.getImage(),72,152,15,13,b); 
                                
                        }
                        if(c.getcIndex()==19)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),72,94,15,13,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),72,107,15,13,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),72,120,15,13,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),72,133,15,13,b);
                            if (c.getnHotels()==1)
                                 g.drawImage(hotel.getImage(),72,94,15,13,b);      
                        }
                        if(c.getcIndex()==29)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),560,75,13,15,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),573,75,13,15,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),586,75,13,15,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),599,75,13,15,b);
                            if (c.getnHotels()==1)
                                g.drawImage(hotel.getImage(),560,75,13,15,b);
                        }
                        if(c.getcIndex()==27)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),442,75,13,15,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),455,75,13,15,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),468,75,13,15,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),481,75,13,15,b);
                             if (c.getnHotels()==1)
                                  g.drawImage(hotel.getImage(),442,75,13,15,b);
                        }
                        if(c.getcIndex()==24)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),268,75,13,15,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),281,75,13,15,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),294,75,13,15,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),307,75,13,15,b);
                            if (c.getnHotels()==1)
                                g.drawImage(hotel.getImage(),268,75,13,15,b);  
                        }
                        if(c.getcIndex()==26)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),385,75,13,15,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),398,75,13,15,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),411,75,13,15,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),424,75,13,15,b);
                            if (c.getnHotels()==1)
                                g.drawImage(hotel.getImage(),385,75,13,15,b);  
                        }
                        if(c.getcIndex()==23)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),210,75,13,15,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),223,75,13,15,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),236,75,13,15,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),249,75,13,15,b);
                             if (c.getnHotels()==1)
                                 g.drawImage(hotel.getImage(),210,75,13,15,b);      
                        }
                        if(c.getcIndex()==21)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),93,75,13,15,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),106,75,13,15,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),119,75,13,15,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),132,75,13,15,b);
                             if (c.getnHotels()==1)
                                 g.drawImage(hotel.getImage(),93,75,13,15,b);    
                        }
                        if(c.getcIndex()==39)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),620,558,15,13,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),620,571,15,13,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),620,584,15,13,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),620,597,15,13,b);
                            if (c.getnHotels()==1)
                                 g.drawImage(hotel.getImage(),620,558,15,13,b); 
                        }
                        if(c.getcIndex()==37)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),620,442,15,13,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),620,455,15,13,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),620,468,15,13,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),620,481,15,13,b);
                            if (c.getnHotels()==1)
                                g.drawImage(hotel.getImage(),620,442,15,13,b);
                        }
                        
                        if(c.getcIndex()==34)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),620,267,15,13,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),620,280,15,13,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),620,293,15,13,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),620,306,15,13,b);
                            if (c.getnHotels()==1)
                                 g.drawImage(hotel.getImage(),620,267,15,13,b);
                        }
                        if(c.getcIndex()==32)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),620,152,15,13,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),620,165,15,13,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),620,178,15,13,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),620,191,15,13,b);
                            if (c.getnHotels()==1)
                                 g.drawImage(hotel.getImage(),620,152,15,13,b); 
                        }
                        if(c.getcIndex()==31)
                        {   if(c.getnHouses()>=1)
                            g.drawImage(house.getImage(),620,94,15,13,b);                        
                            if(c.getnHouses()>=2)
                            g.drawImage(house.getImage(),620,107,15,13,b);
                            if(c.getnHouses()>=3)
                            g.drawImage(house.getImage(),620,120,15,13,b);
                            if(c.getnHouses()==4)
                            g.drawImage(house.getImage(),620,133,15,13,b);
                             if (c.getnHotels()==1)
                                 g.drawImage(hotel.getImage(),620,94,15,13,b);
                        }
                    }
                }
            }
    }
    
    public boolean Sell(int index)
    {
        if(this.getOwnedCountries().get(index) instanceof Country)
        {
            Country c =  (Country) this.getOwnedCountries().get(index);
            if(c.isBought())
            {
                if(c.getnHouses()!=0)
                {
                JOptionPane.showMessageDialog(null, "You must sell houses first");
                return false ; 
                }
                else 
                {    
                  int reply = JOptionPane.showConfirmDialog(null, "Are you sure that you want to sell "+c.getName()+"?", "", JOptionPane.YES_NO_OPTION);
                    if(c.getOwner().equals(this.Name))
                     {
                       if (reply == JOptionPane.YES_OPTION)
                       {
                        c.removeOwner();
                        this.OwnedCountries.remove(c) ; 
                        this.addMoney(c.getCost()/2);
                        c.setBought(false);
                        JOptionPane.showMessageDialog(null, "You sold "+c.getName());
                        Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" has sold "+c.getName());
                        return true; 
                       }
                     else 
                        return false;
                      }
                }
               
            }
            else
            {
                JOptionPane.showMessageDialog(null, "You do not own "+c.getName());
                return false;
            }
        }
        
        else if(this.getOwnedCountries().get(index) instanceof RailRoad)
        {
            RailRoad r = (RailRoad) this.getOwnedCountries().get(index);
            
            if(r.isBought())
            {   
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure that you want to sell "+r.getName()+"?", "", JOptionPane.YES_NO_OPTION);
                if(r.getOwner().equals(this.Name))
                {
                    if (reply == JOptionPane.YES_OPTION)
                    {
                        r.removeOwner();
                        this.OwnedCountries.remove(r) ; 
                        this.addMoney(r.getCost()/2);
                        r.setBought(false);
                        JOptionPane.showMessageDialog(null, "You sold "+r.getName());
                        Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" has sold "+r.getName());
                        return true ; 
                   }
                    
                   else
                    return false;
                }
                
                else
                {
                    JOptionPane.showMessageDialog(null, "You do not own "+r.getName());
                    return false;
                }
            }
            
            else
            {
                JOptionPane.showMessageDialog(null, "You do not own "+r.getName());
                return false;
            }    
        }
        
        else if(this.getOwnedCountries().get(index) instanceof waterelec)
        {
            waterelec w = (waterelec) this.getOwnedCountries().get(index);
                
            if(w.isBought())
            {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure that you want to sell "+w.getName()+"?", "", JOptionPane.YES_NO_OPTION);
                if(w.getOwner().equals(this.Name))
                {
                    if (reply == JOptionPane.YES_OPTION)
                    {
                        w.removeOwner();
                        this.OwnedCountries.remove(w) ; 
                        this.addMoney(w.getCost()/2);
                        w.setBought(false);
                        JOptionPane.showMessageDialog(null, "You sold "+w.getName());
                        Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" has sold "+w.getName());
                        return true ; 
                    }
                    
                    else
                        return false;
                }
                
                else
                {
                    JOptionPane.showMessageDialog(null, "You do not own "+w.getName());
                    return false;
                }
            }
            
            else
            {
                JOptionPane.showMessageDialog(null, "You do not own "+w.getName());
                return false;
            }
        } 
    
       else
            return false; 
        return false;
    }
    
    
    public void Move(boolean Forward)
    {       
        if(Forward)
        {
            if(Index >= 0 && Index <= 9)
            {
                if(Index == 0 || Index == 9)
                    x -= 83;

                else
                    x -= 58;
            }

            else if(Index >= 10 && Index <= 19)
            {
                if(Index == 10 || Index == 19)
                    y -= 83;

                else
                    y -= 58;
            }

            else if(Index >= 20 && Index <= 29)
            {
                if(Index == 20 || Index  == 29)
                    x += 83;

                else
                    x += 58;
            }

            else if(Index  >= 30 && Index <= 39)
            {
                if(Index  == 30 || Index == 39)
                    y+= 83;

                else
                    y += 58;                
            }

            Index = (Index+1)%40;  
            if(Index == 0 && !cannotCollect && !isPrisoned)
            {
                this.Money += 200;
                cannotCollect = false;
            }
        }

        else
        {
            if(Index >= 0 && Index <= 9)
            {
                if(Index == 0 || Index == 9)
                    x += 83;

                else
                    x += 58;
            }

            else if(Index >= 10 && Index <= 19)
            {
                if(Index == 10 || Index == 19)
                    y += 83;

                else
                    y += 58;
            }

            else if(Index >= 20 && Index <= 29)
            {
                if(Index == 20 || Index  == 29)
                    y += 83;

                else
                    x -= 58;
            }

            else if(Index  >= 30 && Index <= 39)
            {
                if(Index  == 30 || Index == 39)
                    y-= 83;

                else
                    y -= 58;                
            }

            Index = (Index-1)%40;  
            if(Index == 0 && !cannotCollect && !isPrisoned)
            {
                this.Money += 200;
                cannotCollect = false;
            }      
        }
    }
    
    public void checkRent(ArrayList <Player> ps,Dice d)
    {
       if(currentLocation instanceof Country)
        {
            Country c = (Country) currentLocation;
            if(c.isBought())
            {
               String name = c.getOwner();
               
                for(int i=0; i< ps.size();i++)
                {
                    if(this.getName().equals(name))
                        return;
                      
                    else if (ps.get(i).getName().equals(name))
                    {   
                        if(CanBuy(c.getRent(),true))
                        {   this.Money -= c.getRent();
                            ps.get(i).addMoney(c.getRent());
                            JOptionPane.showMessageDialog(null,"You paid rent of $"+c.getRent()+" to "+c.getOwner());
                            Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" paid $"+c.getRent()+" to "+c.getOwner());
                        }
                        else 
                        
                            hasLost = true;
                           
                        return;
                    }
                    
                }
            }          
        }
       
       else if(currentLocation instanceof waterelec)
       {
           waterelec w = (waterelec) currentLocation;
           
           if(w.isBought())
           {
               String name = w.getOwner();
               
                if(this.Name == name)
                    return;
               
                for(int i=0; i< ps.size();i++)
                {
                    if (ps.get(i).getName().equals(name))
                    {   
                        ArrayList <Location> l = ps.get(i).getOwnedCountries();
                        String tileName = w.getName();

                        for(int j=0; j<l.size();j++)
                        {
                            if(tileName.equals("Water Works"))
                            {
                                if(DifferentRent)
                                {
                                    if(CanBuy(10*d.getDiceRoll(),true))
                                    {  
                                        DifferentRent = false;
                                        this.Money -= 10*d.getDiceRoll();
                                        ps.get(i).addMoney(10*d.getDiceRoll()); 
                                        JOptionPane.showMessageDialog(null,"You paid $"+10*d.getDiceRoll()+" to "+w.getOwner());
                                        Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" paid $"+10*d.getDiceRoll()+" to "+w.getOwner());
                                    }
                                    else
                                        hasLost = true;
                                }
                                
                                else
                                {
                                    if(l.get(j).getName().equals("Electric Company"))
                                    {
                                        if(CanBuy(10*d.getDiceRoll(),true))
                                        {  
                                            this.Money -= 10*d.getDiceRoll();
                                            ps.get(i).addMoney(10*d.getDiceRoll()); 
                                            JOptionPane.showMessageDialog(null,"You paid $"+10*d.getDiceRoll()+" to "+w.getOwner());
                                            Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" paid $"+10*d.getDiceRoll()+" to "+w.getOwner());
                                        }
                                        else
                                            hasLost = true;
                                    }
                                    else
                                    {
                                        if(CanBuy(4*d.getDiceRoll(),true))
                                        {  
                                            this.Money -= 4*d.getDiceRoll();
                                            ps.get(i).addMoney(4*d.getDiceRoll());
                                            JOptionPane.showMessageDialog(null,"You paid $"+4*d.getDiceRoll()+" to "+w.getOwner());
                                            Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" paid $"+4*d.getDiceRoll()+" to "+w.getOwner());
                                        }
                                        else
                                            hasLost = true;
                                    }
                                    return;
                                }
                            }

                            else if (tileName.equals("Electric Company"))
                            {
                                if(DifferentRent)
                                {
                                    if(CanBuy(10*d.getDiceRoll(),true))
                                    {  
                                        DifferentRent = false;
                                        this.Money -= 10*d.getDiceRoll();
                                        ps.get(i).addMoney(10*d.getDiceRoll()); 
                                        JOptionPane.showMessageDialog(null,"You paid $"+10*d.getDiceRoll()+" to "+w.getOwner());
                                        Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" paid $"+10*d.getDiceRoll()+" to "+w.getOwner());
                                    }
                                    else
                                        hasLost = true;
                                }
                                
                                else
                                {
                                    if(l.get(j).getName().equals("Water Works") )
                                    {
                                        if(CanBuy(10*d.getDiceRoll(),true))
                                        {  
                                            this.Money -= 10*d.getDiceRoll();
                                            ps.get(i).addMoney(10*d.getDiceRoll());
                                            JOptionPane.showMessageDialog(null,"You paid $"+10*d.getDiceRoll()+" to "+w.getOwner());
                                            Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" paid $"+10*d.getDiceRoll()+" to "+w.getOwner());
                                        }
                                        else
                                            hasLost = true;
                                    }
                                    else
                                    {
                                        if(CanBuy(4*d.getDiceRoll(),true))
                                        { 
                                            this.Money -= 4*d.getDiceRoll();
                                            ps.get(i).addMoney(4*d.getDiceRoll());
                                            JOptionPane.showMessageDialog(null,"You paid $"+4*d.getDiceRoll()+" to "+w.getOwner());
                                            Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" paid $"+4*d.getDiceRoll()+" to "+w.getOwner());
                                        }
                                        else

                                            hasLost = true;
                                        
                                    }
                                     return;
                                } 
                            }
                        }
                    return;
                }    
            }
        }
           
        DifferentRent = false;
    }
       
        else if(currentLocation instanceof RailRoad)
        {
           RailRoad r = (RailRoad) currentLocation;
           
           if(r.isBought())
           {
               String name = r.getOwner();
               
               if(this.getName().equals (name))
                    return;
               
               for(int i=0; i< ps.size();i++)
                {
                    if(ps.get(i).getName().equals(name))
                    {   
                        ArrayList <Location> l = ps.get(i).getOwnedCountries();
                        int rent = 25;
                        
                        for(int j=0; j<l.size();j++)
                        {
                            if(l.get(j).getName().equals(r.getName()))
                                continue;
                            
                            else if(l.get(j) instanceof RailRoad)
                                rent*=2;
                        }
                        
                        if(DifferentRent)
                        {
                            rent *= 2;
                            DifferentRent = false;
                        }
                        
                        if(CanBuy(rent,true))
                        { 
                            this.Money -= rent;
                            ps.get(i).addMoney(rent);
                            JOptionPane.showMessageDialog(null,"You paid $"+rent+" to "+r.getOwner());
                            Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" paid $"+rent+" to "+r.getOwner());
                            
                        }
                        
                        else
                            hasLost = true;
                        
                        return;
                    }
                    
                }
            }
           DifferentRent = false;
        }
    } 
    
    public void checkChance(javax.swing.Timer moveTimer,javax.swing.Timer DiceTimer, Dice Dice , Game Game)
    {
        if(currentLocation instanceof Chance)
        {
            Chance c = (Chance) currentLocation;
            c.assignChance(this, moveTimer, DiceTimer, Dice, Game);
        }
    }

    public void checkCommunityChest(javax.swing.Timer moveTimer,javax.swing.Timer DiceTimer, Dice Dice , Game Game)
    {
        if(currentLocation instanceof communityChest )
        {
           communityChest  cc = (communityChest) currentLocation;
           cc.assignCommunityChest(this, moveTimer, DiceTimer, Dice, Game);
        }
    }
    
    public void checkTaxes()
    {
        if(currentLocation.getName().equals("Luxury Tax"))
        {
            if(CanBuy(650,true))
            {
                this.Money -= 650;
                JOptionPane.showMessageDialog(null,"You paid $650 luxury taxes","Luxury Tax",JOptionPane.PLAIN_MESSAGE);
                Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" paid $650 luxury taxes");
            }
        }
        
        else if(currentLocation.getName().equals("Income Tax"))
        {
            double fees = Math.round(0.1*this.Money);
            this.Money -= fees;
            JOptionPane.showMessageDialog(null,"You paid $"+fees+" income taxes","Income Tax",JOptionPane.PLAIN_MESSAGE);
            Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" paid $"+fees+" income taxes");
        }
    }
    
    public void CheckJail(javax.swing.Timer moveTimer,Game Game)
    {
        if (this.currentLocation.getName().equals( "Go to Jail"))
        {
            Object[] options = { "Pay $50", "Go to Jail" };
            int choice = JOptionPane.showOptionDialog(null, "Choose your Fate !", "Warning",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
            
            if(choice== 0)
            {
                if(CanBuy(50,false))
                {
                    Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" paid $50 fine and escaped jail!");
                    PaidForPrison=true;
                }
                
                else
                {
                    JOptionPane.showMessageDialog(null, "You don't have enough money, you must go to jail");
                    Game.setDiceRoll(20);
                    moveTimer.start();
                    isPrisoned = true;
                }
                
            }
         
            else
            {
                Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+this.Name+" was sent to jail!");
                Game.setDiceRoll(20);
                moveTimer.start();
                isPrisoned = true;
            }
        }
    }
    
    public boolean CanBuy (int c , boolean mustPay)
    {
        if ((this.Money - c) < 0)
        {
            if(!(this.OwnedCountries.isEmpty()) && mustPay)
            {
                JOptionPane.showMessageDialog(null, "You don't have enough money to pay $"+c+"\n"+
                        "countries will be sold to the bank until your debt is settled.", "Warning", JOptionPane.WARNING_MESSAGE);
                
                for(int i=0 ; i < this.OwnedCountries.size() ; i++)
                {                  
                    this.Money += this.OwnedCountries.get(i).getCost()/2;
                    this.OwnedCountries.remove(i);
                    Game.Models[Game.current].removeElementAt(i);
                        
                    if(this.Money >= c)
                    {
                        JOptionPane.showMessageDialog(null, "Your debt has been settled, you can continue playing.");
                        return true;
                    }
                }
                
                JOptionPane.showMessageDialog(null, "You don't have enough countries to cover your debt.");
                return false;
            }
            
            else
              return false;
        }
        
        return true;
    }

    public void Kick()
    {
        for(int i=0; i<OwnedCountries.size(); i++)
        {
            Country c = (Country) OwnedCountries.get(i);
            c.setOwner("None");
            c.setBought(false);
        }
    }
}