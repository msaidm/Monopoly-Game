package monopoly;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.*;
import javax.swing.*;


public class Dice extends JPanel implements Serializable
{
   String[] Dice = new String[]{"Resources/dice1.png","Resources/dice2.png","Resources/dice3.png","Resources/dice4.png","Resources/dice5.png","Resources/dice6.png" }; 
   private int Index1 ;
   private int Index2 ;
   private JLabel Die1_Lbl = new JLabel() ;  
   private JLabel Die2_Lbl = new JLabel() ; 
   private ImageIcon Die1 = new ImageIcon("Resources/dice6.png");
   private Image ResizedDie1 = Die1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
   private ImageIcon Die2 = new ImageIcon("Resources/dice6.png");
   private Image ResizedDie2 = Die2.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT);
   private int count = 0 ; 
   private boolean doubleDice = false;
   
   public  Dice() { init() ; }
   
   public int getCount(){return count;}
           
   public void init()
   {
        this.setLayout(null);
        this.setBounds(275,130,170,85);
        setBackground(new Color(0,0,0,1));
        Die1_Lbl.setIcon(new ImageIcon(ResizedDie1));
        Die2_Lbl.setIcon(new ImageIcon(ResizedDie2));
        Die1_Lbl.setBounds(0, 0, 80, 80);
        Die2_Lbl.setBounds(90, 0, 80, 80);

        this.add(Die1_Lbl);
        this.add(Die2_Lbl); 
   }
                
    public void Roll()
    {
        Random r = new Random();
        Index1 =  r.nextInt(6);
        Index2 =  r.nextInt(6);
        if(Index1 == Index2)
            doubleDice = true;
        else 
            doubleDice = false;
        Die1 =  new ImageIcon(Dice[Index1]) ;  ;
        ResizedDie1 =  Die1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT) ;
        Die2 = new ImageIcon(Dice[Index2]) ;
        ResizedDie2 =Die2.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT) ;
        Die1_Lbl.setIcon(new ImageIcon(ResizedDie1));
        Die2_Lbl.setIcon(new ImageIcon(ResizedDie2));  
        count++; 
        repaint();
    }
    
    public int getDiceRoll(){return Index1+Index2+2;}
    
    public void setCount(int n){count = n;} //??
    
    public boolean getIsDouble(){
        return doubleDice;
    }
}
