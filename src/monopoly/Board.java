package monopoly;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


public class Board extends JPanel implements Serializable
{
    private ArrayList<Player> Players = new ArrayList();
    private ArrayList<Tile> Tiles = new ArrayList();
    private int numberOfPlayers;
    
    
    public Board(ArrayList<Player> Players , int numberOfPlayers)
    {
        drawBoard();
        this.Players = Players;
        this.numberOfPlayers = numberOfPlayers;
    }
    
    public void drawBoard()
    {
        setLayout(null);
        setBackground(new Color(85,107,47));
        int x = 615;
        int y = 615;
        Tile t = null;
        for(int i=0 ; i < 40 ; i++)
        {
            if(i >= 0 && i <= 10)
            {
                if(i == 0)
                {
                    t = new Tile("Resources/"+(i+1)+".png",x,y,85,85);
                    x -= 58;
                }
                
                else if( i == 10)
                {
                    t = new Tile("Resources/"+(i+1)+".png",x,y,85,85);
                    y -= 58;
                }
                
                else
                {
                    if(i == 9)
                    {
                        t = new Tile("Resources/"+(i+1)+".png",x,y,58,85);
                        x -= 85;
                    }
                    
                    else
                    {
                        t = new Tile("Resources/"+(i+1)+".png",x,y,58,85);
                        x -= 58;
                    }
                }
            }
            
            else if(i >= 11 && i <= 20)
            {            
                if( i == 20)
                {
                    t = new Tile("Resources/"+(i+1)+".png",x,y,85,85);
                    x += 85;
                }
                
                else
                {
                    if(i == 19)
                    {
                        t = new Tile("Resources/"+(i+1)+".png",x,y,85,58);
                        y -= 85;
                    }
                    
                    else
                    {
                        t = new Tile("Resources/"+(i+1)+".png",x,y,85,58);
                        y -= 58;
                    }
                }
            }
            
            else if(i >= 21 && i <= 30)
            {            
                if( i == 30)
                {
                    t = new Tile("Resources/"+(i+1)+".png",x,y,85,85);
                    y += 85;
                }
                
                else
                {
                    t = new Tile("Resources/"+(i+1)+".png",x,y,58,85);
                    x += 58;                   
                }
            }
            
            else if(i >= 31 && i <= 39)
            {            
                t = new Tile("Resources/"+(i+1)+".png",x,y,85,58);
                y += 58;
            }
            
            add(t);
            Tiles.add(t);
        }      
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        for(int i=0 ; i < numberOfPlayers ; i++)
            Players.get(i).draw(g, this);
        
        
    }

    public ArrayList<Player> getPlayers()
    {
        return Players;
    }

    public void setPlayers(ArrayList<Player> Players) 
    {
        this.Players = Players;
    }

    public int getNumberOfPlayers() 
    {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers)
    {
        this.numberOfPlayers = numberOfPlayers;
    }
}
