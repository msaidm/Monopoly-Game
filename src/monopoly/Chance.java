package monopoly;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Chance extends Location implements Serializable
{
    String[] card = new String[]
    {
        "Advance to Go (Collect $200)",
        " Advance to Illinois Ave—If you pass Go, collect $200",
        "Advance to East Village – If you pass Go, collect $200",
        "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.",
        "Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.",
        "Bank pays you dividend of $50",
        "Get Out of Jail Free",
        "Go Back 3 Spaces",
        "Go to Jail–Go directly to Jail–Do not pass Go, do not collect $200",
        "Make general repairs on all your property–For each house pay $25",
        "Pay poor tax of $15",
        "Take a trip to Reading Railroad–If you pass Go, collect $200",
        "Take a walk on Wall Street–Advance token to Wall Street",
        "You have been elected Chairman of the Board–Pay each player $50",
        "Your building and loan matures—Collect $150",
        "You have won a crossword competition—Collect $100"
    };

    public Chance(String s) {
        super(s,null);
    }

    public void assignChance(Player p, javax.swing.Timer moveTimer, javax.swing.Timer DiceTimer, Dice Dice, Game Game) 
    {
        Random r = new Random();
        int index = r.nextInt(16);
        if (index == 0) 
        {
            JOptionPane.showMessageDialog(null,card[0],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            Game.setDiceRoll(40 - p.getIndex());
            moveTimer.start();
        }
        
        if (index == 1) 
        {
            JOptionPane.showMessageDialog(null,card[1],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            if (p.getIndex() == 7)
            {
                Game.setDiceRoll(17);
                moveTimer.start();
            }

            if (p.getIndex() == 22)
            {
                Game.setDiceRoll(2);
                moveTimer.start();
            }

            if (p.getIndex() == 36) 
            {
                Game.setDiceRoll(28);
                moveTimer.start();
            }
            moveTimer.start();
        }
        
        if (index == 2) 
        {
            JOptionPane.showMessageDialog(null,card[2],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            if (p.getIndex() == 7)
            {
                Game.setDiceRoll(4);
                moveTimer.start();
            }

            if (p.getIndex() == 22)
            {
                Game.setDiceRoll(29);
                moveTimer.start();
            }

            if (p.getIndex() == 36) 
            {
                Game.setDiceRoll(15);
                moveTimer.start();
            }
            moveTimer.start();
        }
        
        if (index == 3)
        {
            JOptionPane.showMessageDialog(null,card[3],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            if (p.getIndex() == 7)
            {
                Game.setDiceRoll(5);
                moveTimer.start();
            }

            if (p.getIndex() == 22)
            {
                Game.setDiceRoll(6);
                moveTimer.start();
            }

            if (p.getIndex() == 36) 
            {
                Game.setDiceRoll(16);
                moveTimer.start();
            }
            
            p.setDifferentRent(true);
        }
        
        if (index == 4)
        {
            JOptionPane.showMessageDialog(null,card[4],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            if (p.getIndex() == 7)
            {
                Game.setDiceRoll(8);
                moveTimer.start();           
            }

            if (p.getIndex() == 22)
            {
                Game.setDiceRoll(3);
                moveTimer.start();
            }

            if (p.getIndex() == 36) 
            {
                Game.setDiceRoll(9);
                moveTimer.start();
            }
            
            p.setDifferentRent(true);
        }
        
        if (index == 5)
        {
            JOptionPane.showMessageDialog(null,card[5],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            p.addMoney(50);
        }
        
        if (index == 6) 
        {
            JOptionPane.showMessageDialog(null,card[6],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            p.setFreePass(true);
        }
        
        if (index == 7) 
        {
            JOptionPane.showMessageDialog(null,card[7],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            p.setDirection(false);
            Game.setDiceRoll(3);
            moveTimer.start();

        }
        
        if (index == 8)
        {
            JOptionPane.showMessageDialog(null,card[8],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            p.setPrisoned(true);
            p.CannotCollect();
            if(p.getIndex() == 7)
                Game.setDiceRoll(3);
            
            if(p.getIndex() == 22)
                Game.setDiceRoll(28);
            
            if(p.getIndex() == 36)
                Game.setDiceRoll(14);
            
            moveTimer.start();
        }
        
        if (index == 9)
        {
            JOptionPane.showMessageDialog(null,card[9],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            ArrayList <Location> cc= p.getOwnedCountries();
            for(int i=0;i<cc.size();i++)
            {
                if(cc.get(i) instanceof Country)
                {
                    Country c = (Country)cc.get(i);
                    if(c.getnHouses()>0)
                        if(p.CanBuy(25*c.getnHouses(), true))
                            p.deductMoney(25*c.getnHouses());
                    
                        else
                            p.setLost(true);
                }
                
            }
        }
        if (index == 10)
        {
            JOptionPane.showMessageDialog(null,card[10],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            if(p.CanBuy(15, true))
                p.deductMoney(15);
            
            else
                p.setLost(true);
        }
        
        if (index == 11) 
        {
            JOptionPane.showMessageDialog(null,card[11],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            Game.setDiceRoll(45 - p.getIndex());
            moveTimer.start();
        }
        
        if (index == 12) 
        {
            JOptionPane.showMessageDialog(null,card[12],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            Game.setDiceRoll(39 - p.getIndex());
            moveTimer.start();
        }
        
        if (index == 13) 
        {
            JOptionPane.showMessageDialog(null,card[13],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            if(p.CanBuy(50*Game.getPlayers().size(), true))
            {
                p.deductMoney(50 * Game.getPlayers().size());
                
                for (int i = 0; i < Game.getPlayers().size(); i++)
                Game.getPlayers().get(i).addMoney(50);
            }
            
            else
                p.setLost(true);
            
            
        }
        
        if (index == 14)
        {
            JOptionPane.showMessageDialog(null,card[14],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            p.addMoney(150 * Game.getPlayers().size());
        }
        
        if (index == 15) 
        {
            JOptionPane.showMessageDialog(null,card[15],"CHANCE",JOptionPane.PLAIN_MESSAGE);
            p.addMoney(100);
        }
    }
}
