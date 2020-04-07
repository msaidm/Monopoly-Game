package monopoly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DiceFrame extends JFrame
{
    private int numberOfPlayers;
    private String[] names;
    private int[] results;
    private boolean Conflict = false;
    private int count = 0;
    private int DiceConst = 10;
    private Die Dice = new Die();
    
    private JButton Roll = new JButton("Roll");
    private JButton Play = new JButton("Play");
    
    private JLabel P1NameLbl;
    private JLabel P2NameLbl;
    private JLabel P3NameLbl;
    private JLabel P4NameLbl;
    
    private JTextField[] PNums;
    private JTextField P1Num;
    private JTextField P2Num;
    private JTextField P3Num;
    private JTextField P4Num;
    
    
    public DiceFrame(int n, String[] names)
    {
        this.numberOfPlayers = n;
        this.names = names;
        results = new int[numberOfPlayers];
        PNums = new JTextField[numberOfPlayers];
        Container c = getContentPane();
        setContentPane(new JLabel(new ImageIcon("Resources/Background2.jpg")));
        setSize(1000,730);
        setTitle("Monopoly");
        setLocation(150,0);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        setLayout(null);
            
        InitializePlayerComponents();
        
        add(Play);
        add(Roll);
        
        Play.setBounds(435,530,80,30);
        Roll.setBounds(520,530,80,30);
        
        Play.setEnabled(false);
        Play.addActionListener(new PlayButtonListener());
        Roll.addActionListener(new RollButtonListener());
    }
    
    public void InitializePlayerComponents()
    {
        if(numberOfPlayers==2)
        {
            P1NameLbl = new JLabel(names[0]);
            P1NameLbl.setForeground(Color.red);
            P1NameLbl.setBounds(500,320,300,40);
            
            P2NameLbl = new JLabel(names[1]);
            P2NameLbl.setForeground(Color.red);
            P2NameLbl.setBounds(500,360,300,40);
            
            PNums[0] = new JTextField();
            PNums[0].setEditable(false);
            PNums[0].setBounds(430,320,30,30);
            
            PNums[1] = new JTextField();
            PNums[1].setEditable(false);
            PNums[1].setBounds(430,360,30,30);
            
            add(P1NameLbl);
            add(P2NameLbl);
            add(PNums[0]);
            add(PNums[1]);
        }
        
        else if(numberOfPlayers==3)
        {
            P1NameLbl = new JLabel(names[0]);
            P1NameLbl.setForeground(Color.red);
            P1NameLbl.setBounds(500,320,300,40);
            
            P2NameLbl = new JLabel(names[1]);
            P2NameLbl.setForeground(Color.red);
            P2NameLbl.setBounds(500,360,300,40);
            
            P3NameLbl = new JLabel(names[2]);
            P3NameLbl.setForeground(Color.red);
            P3NameLbl.setBounds(500,400,300,40);
            
            PNums[0] = new JTextField();
            PNums[0].setEditable(false);
            PNums[0].setBounds(430,320,30,30);
            
            PNums[1] = new JTextField();
            PNums[1].setEditable(false);
            PNums[1].setBounds(430,360,30,30);
            
            PNums[2] = new JTextField();
            PNums[2].setEditable(false);
            PNums[2].setBounds(430,400,30,30);
            
            add(P1NameLbl);
            add(P2NameLbl);
            add(P3NameLbl);
            add(PNums[0]);
            add(PNums[1]);
            add(PNums[2]);
        }
        
        else if(numberOfPlayers==4)
        {
            P1NameLbl = new JLabel(names[0]);
            P1NameLbl.setForeground(Color.red);
            P1NameLbl.setBounds(500,320,300,40);
            
            P2NameLbl = new JLabel(names[1]);
            P2NameLbl.setForeground(Color.red);
            P2NameLbl.setBounds(500,360,300,40);
            
            P3NameLbl = new JLabel(names[2]);
            P3NameLbl.setForeground(Color.red);
            P3NameLbl.setBounds(500,400,300,40);
            
            P4NameLbl = new JLabel(names[3]);
            P4NameLbl.setForeground(Color.red);
            P4NameLbl.setBounds(500,440,300,40);
            
            PNums[0] = new JTextField();
            PNums[0].setEditable(false);
            PNums[0].setBounds(430,320,30,30);
            
            PNums[1] = new JTextField();
            PNums[1].setEditable(false);
            PNums[1].setBounds(430,360,30,30);
            
            PNums[2] = new JTextField();
            PNums[2].setEditable(false);
            PNums[2].setBounds(430,400,30,30);
            
            PNums[3] = new JTextField();
            PNums[3].setEditable(false);
            PNums[3].setBounds(430,440,30,30);
            
            add(P1NameLbl);
            add(P2NameLbl);
            add(P3NameLbl);
            add(P4NameLbl);
            add(PNums[0]);
            add(PNums[1]);
            add(PNums[2]);
            add(PNums[3]);
        }
    }
    
    public boolean SameNumbers()
    {
        for(int i=0 ; i < numberOfPlayers ; i++)
            for(int j=0 ; j < numberOfPlayers ; j++)
            {
                if(i == j)
                    continue;
                
                else
                    if(results[i] == results[j])
                        return true;
                
            }
        return false;
    }
    
    public void getPlayerRolls()
    {
        for(int i=0 ; i < numberOfPlayers ; i++)
            {
                results[i] = Dice.Roll();
                PNums[i].setText(results[i]+"");
            }
    }
    
    public void reSortPlayers()
    {
        String[] s = new String[numberOfPlayers];
        
         for(int i=0 ; i < numberOfPlayers ; i++)
                s[i] = names[getMaxIndex()];
         
         names = s;
    }
    
    public int getMaxIndex()
    {
        int maxIndex = 0;
        for(int i=0 ; i < numberOfPlayers ; i++)
            if(results[i] > results[maxIndex])
                maxIndex = i;
        
        results[maxIndex] = 0;
        return maxIndex;
    }  
    
    public String getMessage()
    {
        String n = "";
        for(int i=0 ; i < numberOfPlayers ; i++)
            n+=names[i]+" is "+"Token("+(i+1)+")\n";
        
        return n;       
    }
    
    class RollButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            getPlayerRolls();
            while(SameNumbers())
            {
                getPlayerRolls();
            }
            
            Roll.setEnabled(false);
            reSortPlayers();
            Play.setEnabled(true);
        }
    }
    
    class PlayButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            boolean startFlag = false;
            int choice = JOptionPane.showConfirmDialog(null, getMessage()+"Ready to play?", "Ready?", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION)
                startFlag = true;
            
            else
                startFlag = false;
            
            if(startFlag)
            {
                Game Monopoly = new Game(numberOfPlayers,names);
                Play.setEnabled(false);
                setVisible(false);
            }           
        }
    }
}
