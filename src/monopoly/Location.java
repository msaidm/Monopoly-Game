package monopoly;

import java.awt.Color;
import java.io.*;
import java.util.ArrayList;

public class Location implements Serializable
{
    private String Name;
    private Color c;
    
    public Color getColor(){return c;}
    
    public String getName(){return Name;}

    public void setName(String Name){this.Name = Name;}

    public Location(String Name,Color c){this.Name = Name; this.c = c;}
     
    public String toString()
    {
        return
                "Name: "+Name+"\n";
    }
    
    public int getCost()
    {
        return 0;
    }
    
    public static Color getTileColor(String s, ArrayList<Location> Countries)
    {
        for(int i=0 ; i < Countries.size() ; i++)
            if(Countries.get(i).getName().equals(s))
                return Countries.get(i).getColor();
        
        return null;
    }
}
