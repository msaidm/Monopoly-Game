package monopoly;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class StartFrame extends JFrame
{
    private JTextField P1Name = new JTextField();
    private JTextField P2Name = new JTextField();
    private JTextField P3Name = new JTextField();
    private JTextField P4Name = new JTextField();
    private JTextField playerNumber = new JTextField();
    
    private JLabel P1NameLbl = new JLabel("Player 1");
    private JLabel P2NameLbl = new JLabel("Player 2");
    private JLabel P3NameLbl = new JLabel("Player 3");
    private JLabel P4NameLbl = new JLabel("Player 4");
    private JLabel playerNumberLbl = new JLabel("number of Players");
    
    private JButton Btn1 = new JButton("Start Game");
    private JButton Btn2 = new JButton("Set number");
    private JButton Btn3 = new JButton ("Load Game");
    
    private int n;
    public StartFrame()
    {
        Container c = getContentPane();
        setContentPane(new JLabel(new ImageIcon("Resources/Background2.jpg")));
        setSize(1000,730);
        setTitle("Monopoly");
        setLocation(150,0);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        setLayout(null);
        
        P1Name.setEditable(false);
        P2Name.setEditable(false);
        P3Name.setEditable(false);
        P4Name.setEditable(false);
        
        P1Name.setBounds(600,250,180,30);
        P2Name.setBounds(600,290,180,30);
        P3Name.setBounds(600,330,180,30);
        P4Name.setBounds(600,370,180,30);
        playerNumber.setBounds(750, 410,30,30);
        
        P1NameLbl.setBounds(785,250,100,30);
        P1NameLbl.setForeground(Color.red);
        
        P2NameLbl.setBounds(785,290,100,30);
        P2NameLbl.setForeground(Color.red);
        
        P3NameLbl.setBounds(785,330,100,30);
        P3NameLbl.setForeground(Color.red);
        
        P4NameLbl.setBounds(785,370,100,30);
        P4NameLbl.setForeground(Color.red);
        
        playerNumberLbl.setBounds(785,410,150,20);
        playerNumberLbl.setForeground(Color.red);
        
        add(P1Name);
        add(P2Name);
        add(P3Name);
        add(P4Name);
        add(playerNumber);
        
        add(P1NameLbl);
        add(P2NameLbl);
        add(P3NameLbl);
        add(P4NameLbl);
        add(playerNumberLbl);
        
        add(Btn1);
        add(Btn2);
        add(Btn3);
        
        Btn1.setBounds(430,420,120,30);
        Btn2.setBounds(600,410,140,30);
        Btn3.setBounds(430,470,120,30);
       
        
        Btn1.addActionListener(new Btn1Listener());
        Btn2.addActionListener(new Btn2Listener());
        Btn3.addActionListener(new Btn3Listener());
    }
    
    class Btn1Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String[] names = new String[n];
            boolean startFlag = false;
            
            if(playerNumber.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Enter number of players");
                return;
            }
            
            if(n == 2)
            {
                if(P1Name.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Enter a name for player 1!");
                
                else if(P1Name.getText().length() > 20)
                {
                    P1Name.setText("");
                    JOptionPane.showMessageDialog(null, "Player 1's name exceeded character limit(max 20)"); 
                }
                
                else
                    names[0] = P1Name.getText();
                
                if(P2Name.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Enter a name for player 2!");
                
                else if(P2Name.getText().length() > 20)
                {
                    P2Name.setText("");
                    JOptionPane.showMessageDialog(null, "Player 2's name exceeded character limit(max 20)"); 
                }
                
                else
                    names[1] = P2Name.getText();
                
                if(names[0] != null && names[1] != null)
                    startFlag = true;

            }
            
            else if(n == 3)
            {
                if(P1Name.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Enter a name for player 1!");
                
                else if(P1Name.getText().length() > 20)
                {
                    P1Name.setText("");
                    JOptionPane.showMessageDialog(null, "Player 1's name exceeded character limit(max 20)"); 
                }
                
                else
                    names[0] = P1Name.getText();
                
                if(P2Name.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Enter a name for player 2!");
                
                else if(P2Name.getText().length() > 20)
                {
                    P2Name.setText("");
                    JOptionPane.showMessageDialog(null, "Player 2's name exceeded character limit(max 20)"); 
                }
                
                else
                    names[1] = P2Name.getText();
                
                if(P3Name.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Enter a name for player 3!");
                
                else if(P3Name.getText().length() > 20)
                {
                    P3Name.setText("");
                    JOptionPane.showMessageDialog(null, "Player 3's name exceeded character limit(max 20)"); 
                }
                
                else
                    names[2] = P3Name.getText();
                
                if(names[0] != null && names[1] != null && names[2] != null)
                    startFlag = true;
            }
            
            else if(n == 4)
            {
                if(P1Name.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Enter a name for player 1!");
                
                else if(P1Name.getText().length() > 20)
                {
                    P1Name.setText("");
                    JOptionPane.showMessageDialog(null, "Player 1's name exceeded character limit(max 20)");  
                }
                
                else
                    names[0] = P1Name.getText();
                
                if(P2Name.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Enter a name for player 2!");
                
                else if(P2Name.getText().length() > 20)
                {
                    P2Name.setText("");
                    JOptionPane.showMessageDialog(null, "Player 2's name exceeded character limit(max 20)"); 
                }
                
                else
                    names[1] = P2Name.getText();
                
                if(P3Name.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Enter a name for player 3!");
                
                else if(P3Name.getText().length() > 20)
                {
                    P3Name.setText("");
                    JOptionPane.showMessageDialog(null, "Player 3's name exceeded character limit(max 20)"); 
                }
                
                else
                    names[2] = P3Name.getText();
                
                if(P4Name.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Enter a name for player 4!");
                
                else if(P4Name.getText().length() > 20)
                {
                    P4Name.setText("");
                    JOptionPane.showMessageDialog(null, "Player 4's name exceeded character limit(max 20)"); 
                }
                
                else
                    names[3] = P4Name.getText();
                
                if(names[0] != null && names[1] != null && names[2] != null && names[3] != null)
                    startFlag = true;
            }
 
            for(int i=0 ; i < names.length-1 ; i++)
            {
                for(int j=i+1 ; j < names.length ; j++)
                {
                    if(i == j)
                        continue;
                    
                    if(names[i] != null && names[j] != null && names[i].equalsIgnoreCase(names[j]))
                    {
                        JOptionPane.showMessageDialog(null, "Players "+(i+1)+" and "+(j+1)+" have the same name!");
                        startFlag = false;
                    }
                }
            }
            
            if(startFlag)
            {
                Btn1.setEnabled(false);
                DiceFrame f = new DiceFrame(n,names);
                setVisible(false);
            }
        }
    }
    
    class Btn2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                n = Integer.parseInt(playerNumber.getText());
                if(n <= 0 || n > 4 || n == 1)
                    JOptionPane.showMessageDialog(null, "Enter a number between 2 and 4!");
                
                else
                {
                    if(n == 2)
                    {
                        P1Name.setEditable(true);
                        P2Name.setEditable(true);
                        P3Name.setEditable(false);
                        P4Name.setEditable(false);
                    }
                    
                    else if(n == 3)
                    {
                        P1Name.setEditable(true);
                        P2Name.setEditable(true);
                        P3Name.setEditable(true);
                        P4Name.setEditable(false);
                    }
                    
                    else if(n == 4)
                    {
                        P1Name.setEditable(true);
                        P2Name.setEditable(true);
                        P3Name.setEditable(true);
                        P4Name.setEditable(true);
                    }
                }

            }
            
            catch(Exception E)
            {
                JOptionPane.showMessageDialog(null, "Enter a number!");
            }
        }
    }
    
    class Btn3Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                FileInputStream fis = new FileInputStream("SavedGame.data");
                ObjectInputStream ois = new ObjectInputStream(fis);
                
                int n = ois.readInt();
                
                int current = ois.readInt();
                
                Player currPlayer = (Player) ois.readObject();
                
                String[] names = new String[n];
                for(int i=0 ; i < n ; i++)
                    names[i] = ois.readUTF();
                
                Player[] players = new Player[n];
                for(int i=0 ; i < n ; i++)
                    players[i] = (Player) ois.readObject();
                   
                Boolean[] states = new Boolean[7];
                for(int i=0 ; i < 7 ; i++)
                    states[i] = ois.readBoolean();
             
                ArrayList<Location> Countries = (ArrayList<Location>) ois.readObject();
                
                String log = ois.readUTF();
                Game g = new Game(n,names);
                g.loadGame(n, current, players, currPlayer, names, states, Countries,log);
                setVisible(false);
            }
            
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, "No saved Data found");
            }
        }
    }
}
