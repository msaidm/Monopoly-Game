package monopoly;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class Game extends JFrame implements Serializable
{
    private Container c;
    private JTextArea Info = new JTextArea();
    static JTextArea InfoLog = new JTextArea();
    
    private Board Board; 
    
    private JPanel Data = new JPanel();
    
    private JButton Buy = new JButton("Buy"); 
    private JButton Sell = new JButton("Sell"); 
    private JButton Roll = new JButton("Roll"); 
    private JButton InfoButton = new JButton("View Tile Information");
    private JButton EndTurn = new JButton("End Turn");
    private JButton BuildHouse = new JButton("Build House");
    private JButton BuildHotel = new JButton("Build Hotel");
    private JButton SellHotel = new JButton("Sell Hotel");
    private JButton SellHouse = new JButton("Sell House");
    private JButton Save = new JButton("Save");
    private JButton Exit = new JButton("New game");
    
    private JLabel InfoLbl = new JLabel("Tile Information");
    private JLabel P1Lbl;
    private JLabel P2Lbl;
    private JLabel P3Lbl;
    private JLabel P4Lbl;
    
    private DefaultListModel<String> modelP1 = new DefaultListModel<>();
    private JList<String> listP1 = new JList<>( modelP1 );
    private DefaultListModel<String> modelP2 = new DefaultListModel<>();
    private JList<String> listP2 = new JList<>( modelP2 );
    private DefaultListModel<String> modelP3 = new DefaultListModel<>();
    private JList<String> listP3 = new JList<>( modelP3 );
    private DefaultListModel<String> modelP4 = new DefaultListModel<>();
    private JList<String> listP4 = new JList<>( modelP4 );
  
    static DefaultListModel<String>[] Models;
    
   
    
    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
    private Player currPlayer;
    
    static int current = 0;
    private int numberOfPlayers;
    
    private String[] names;
    
    private ArrayList<Player> Players = new ArrayList();

    private ArrayList<Location> Countries = new ArrayList();
    
    private Dice Dice = new Dice();
    
    int DiceRoll;
    int DiceConst = 10;
    
    private boolean isFirstTurn = true;
            
    private javax.swing.Timer motionTimer = new javax.swing.Timer(50,new motionListener());
    private javax.swing.Timer DiceTimer = new javax.swing.Timer(50,new DiceListener());
    
    public Game(int n,String[] names)
    {
        numberOfPlayers = n;
        this.names = names;
        initializePlayers(n,names);
        initializeCountries(); 
        initializeComponents(); 
        updateLists();      
    } 
    
    public void initializeComponents() 
    {
        TitledBorder InfoLogName = BorderFactory.createTitledBorder("News Log");
        TitledBorder InfoTitle = BorderFactory.createTitledBorder("Tile Info");
        c = getContentPane(); 
        setSize(1000,730);
        setTitle("Monopoly");
        setLocation(150,0);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Data.setPreferredSize(new Dimension(293,700));
        Data.setBackground(new Color(85,107,47));
        
        InfoLog.setBounds(100, 390, 165 ,200);
        
        InfoLog.setEditable(false);
        InfoLog.setBorder(InfoLogName);
        
        Buy.setPreferredSize(new Dimension(80,40));
        Buy.setBounds(210,260,80,40);
        Buy.setEnabled(false);
        InfoButton.setBounds(270,215,180,40);
        Roll.setBounds(320,260,80,40);
        Info.setBounds(270,390,180,200);
        EndTurn.setBounds(300,305,120,40);
        EndTurn.setEnabled(false);
        Info.setEditable(false);
        Info.setBorder(InfoTitle);
        Sell.setPreferredSize(new Dimension(80,40));
        Sell.setBounds(430,260,80,40);
        BuildHouse.setPreferredSize(new Dimension(120,40));
        BuildHouse.setBounds(170,305,120,40);
        BuildHouse.setEnabled(false);
        BuildHotel.setPreferredSize(new Dimension(120,40));
        BuildHotel.setBounds(170,350,120,40);
        BuildHotel.setEnabled(false);
        SellHotel.setPreferredSize(new Dimension(120,40));
        SellHotel.setBounds(430,350,120,40);
        SellHouse.setEnabled(false);
        SellHouse.setPreferredSize(new Dimension(120,40));
        SellHouse.setBounds(430,305,120,40);
        SellHouse.setEnabled(false);
        Save.setBounds(460,410,120,40);
        Exit.setBounds(460,455,120,40);
        
        
        Board = new Board(Players,numberOfPlayers);
        Board.repaint();
        Board.add(Buy);
        Board.add(Roll);
        Board.add(InfoButton);
        Board.add(EndTurn);
        Board.add(Info);
        Board.add(Dice);
        Board.add(Sell);
        Board.add(BuildHouse);
        Board.add(BuildHotel);
        JScrollPane ScrollableLog = new JScrollPane(InfoLog);
        ScrollableLog.setBounds(100, 390, 165 ,200);
        Board.add(ScrollableLog);
        Board.add(Save);
        Board.add(Exit);
        Board.add(SellHouse);
        Board.add(SellHotel);
        c.add(Board);
        c.add(Data , BorderLayout.WEST) ; 

        InfoButton.addActionListener(new InfoButtonListener());  
        Roll.addActionListener(new RollButtonListener());
        Buy.addActionListener(new BuyButtonListener());
        EndTurn.addActionListener(new EndButtonListener());
        Sell.addActionListener(new SellButtonListener());
        BuildHouse.addActionListener(new BtnBuildListener());
        SellHouse.addActionListener(new SellHousesBtnListener());
        BuildHotel.addActionListener(new BtnHotelBuildListener());
        SellHotel.addActionListener(new BtnHotelSellListener());
        Save.addActionListener(new SaveBtnListener());
        Exit.addActionListener(new ExitBtnListener());
        setVisible(true); 
    }
 
    public ArrayList<Player> getPlayers(){return Players;}
    
    public void setDiceRoll(int x) { this.DiceRoll=x;}
        
    private void savePlayerCount(ObjectOutputStream oos)
    {
        try
        {
            oos.writeInt(numberOfPlayers);
        }
        
        catch(Exception e)
        {
            
        }
    }
    private void saveCurrentTurn(ObjectOutputStream oos)
    {
        try
        {
            oos.writeInt(current);
        }
        
        catch(Exception e)
        {
            
        }
    }
    private void saveCurrentPlayer(ObjectOutputStream oos)
    {
        try
        {
            oos.writeObject(currPlayer);
        }
        
        catch(Exception e)
        {
            
        }
    }
    private void savePlayerNames(ObjectOutputStream oos)
    {
        try
        {
            for(int i=0 ; i < numberOfPlayers ; i++)
                    oos.writeUTF(names[i]);
        }
        
        catch(Exception e)
        {
            
        }
    }
    private void savePlayers(ObjectOutputStream oos)
    {
        try
        {
           for(int i=0 ; i < numberOfPlayers ; i++)
                oos.writeObject(Players.get(i)); 
        }
        
        catch(Exception e)
        {
            
        }
    }
    private void saveButtonStates(ObjectOutputStream oos)
    {
        try
        {
            oos.writeBoolean(Roll.isEnabled());
            oos.writeBoolean(EndTurn.isEnabled());
            oos.writeBoolean(Buy.isEnabled());
            oos.writeBoolean(BuildHouse.isEnabled());
            oos.writeBoolean(SellHouse.isEnabled());
            oos.writeBoolean(BuildHotel.isEnabled());
            oos.writeBoolean(SellHotel.isEnabled());
        }
        
        catch(Exception e)
        {
            
        }
    }
    private void saveCountries(ObjectOutputStream oos)
    {
        try
        {
            oos.writeObject(Countries);
        }
        
        catch(Exception e)
        {
            
        }
    }
    private void saveLog(ObjectOutputStream oos)
    {
        try
        {
            oos.writeUTF(InfoLog.getText());
        }
        
        catch(Exception e)
        {
            
        }
    }
    public void saveGame(ObjectOutputStream oos)
    {
        savePlayerCount(oos);
        saveCurrentTurn(oos);
        saveCurrentPlayer(oos);
        savePlayerNames(oos);
        savePlayers(oos);
        saveButtonStates(oos);
        saveCountries(oos);
        saveLog(oos);
    }
    
    
    private void loadPlayerCount(int count)
    {numberOfPlayers = count;}
    private void loadCurrentTurn(int curr)
    {this.current = curr;}
    private void loadLists()
    {
        if( numberOfPlayers == 2)
        {
            for(int i=0 ; i < p1.getOwnedCountries().size() ; i++)
                modelP1.addElement(p1.getOwnedCountries().get(i).getName());
            
            for(int i=0 ; i < p2.getOwnedCountries().size() ; i++)
                modelP2.addElement(p2.getOwnedCountries().get(i).getName());
        }
        
        if(numberOfPlayers == 3)
        {
            for(int i=0 ; i < p1.getOwnedCountries().size() ; i++)
                modelP1.addElement(p1.getOwnedCountries().get(i).getName());
            
            for(int i=0 ; i < p2.getOwnedCountries().size() ; i++)
                modelP2.addElement(p2.getOwnedCountries().get(i).getName());
            
            for(int i=0 ; i < p3.getOwnedCountries().size() ; i++)
                modelP3.addElement(p3.getOwnedCountries().get(i).getName());
        }
        
        if(numberOfPlayers == 4)
        {
            for(int i=0 ; i < p1.getOwnedCountries().size() ; i++)
                modelP1.addElement(p1.getOwnedCountries().get(i).getName());
            
            for(int i=0 ; i < p2.getOwnedCountries().size() ; i++)
                modelP2.addElement(p2.getOwnedCountries().get(i).getName());
            
            for(int i=0 ; i < p3.getOwnedCountries().size() ; i++)
                modelP3.addElement(p3.getOwnedCountries().get(i).getName());
            
            for(int i=0 ; i < p4.getOwnedCountries().size() ; i++)
                modelP4.addElement(p4.getOwnedCountries().get(i).getName());
        }
    }
    private void loadPlayers(Player[] players)
    {
      if(players.length == 2)
        {
            p1 = players[0];
            p2 = players[1];
        }
        
        if(players.length == 3)
        {
            p1 = players[0];
            p2 = players[1];
            p3 = players[2];
        }
        
        if(players.length == 4)
        {
            p1 = players[0];
            p2 = players[1];
            p3 = players[2];
            p4 = players[3];
        }
        
        for(int i=0 ; i < players.length ; i++)
            Players.set(i,players[i]);
    }
    private void loadCurrentPlayer(Player p)
    {
        currPlayer = p;
    }
    private void loadPlayerNames(String[] names)
    {
        this.names = names;
    }
    private void loadButtonStates(Boolean[] states)
    {
        Roll.setEnabled(states[0]);
        EndTurn.setEnabled(states[1]);
        Buy.setEnabled(states[2]);
        BuildHouse.setEnabled(states[3]);
        SellHouse.setEnabled(states[4]);
        BuildHotel.setEnabled(states[5]);
        SellHotel.setEnabled(states[6]);
    }
    private void loadCountries(ArrayList<Location> Countries)
    {
        this.Countries = Countries;
    }
    private void loadInfoLog(String s)
    {
        InfoLog.setText(s);
    }
    public void loadGame(int count,int curr,Player[] players , Player p , String[] names , Boolean[] states , ArrayList<Location> Countries,String s)
    {
        loadPlayerCount(count);
        loadCurrentTurn(curr);
        loadCurrentPlayer(p);
        loadPlayerNames(names);
        loadPlayers(players);
        loadLists();
        loadButtonStates(states);
        loadCountries(Countries);
        loadInfoLog(s);
        updateLabels();
        updateLists();
        Board.repaint();
    }
    
    void initializePlayers(int n, String[] names)
    {
        if(n == 2)
        {
            p1 = new Player(names[0],"Resources/p1.png",640,640);
            p2 = new Player(names[1],"Resources/p2.png",667,640);
            
            P1Lbl = new JLabel(p1.getName()+" : $"+p1.getMoney());
            P2Lbl = new JLabel(p2.getName()+" : $"+p2.getMoney());
            
            TitledBorder P1Title = BorderFactory.createTitledBorder(p1.getName()+" Properties");
            TitledBorder P2Title = BorderFactory.createTitledBorder(p2.getName()+" Properties");
            
            Data.add(P1Lbl); 
            Data.add(listP1);

            Data.add(P2Lbl); 
            Data.add(listP2);
            
            P1Lbl.setPreferredSize(new Dimension(293,15));
            P1Lbl.setFont((new Font("Arial",Font.BOLD,15)));
            P1Lbl.setForeground(Color.white);
            
            P2Lbl.setPreferredSize(new Dimension(293,15));
            P2Lbl.setFont((new Font("Arial",Font.BOLD,15)));
            P2Lbl.setForeground(Color.white);
          
            listP1.setPreferredSize(new Dimension(293,300));
            listP1.setFont(new Font("Arial",Font.PLAIN,12));
            listP1.setBorder(P1Title);
            listP1.setCellRenderer(new MyCellRenderer(Countries));  
            
            listP2.setPreferredSize(new Dimension(293,300));
            listP2.setFont(new Font("Arial",Font.PLAIN,12));         
            listP2.setBorder(P2Title);
            listP2.setCellRenderer(new MyCellRenderer(Countries));
            
            Players.add(p1);
            Players.add(p2); 
            
            Models = new DefaultListModel[2];
            Models[0] = modelP1;
            Models[1] = modelP2;
        }
        
        else if(n == 3)
        {
            p1 = new Player(names[0],"Resources/p1.png",640,640);
            p2 = new Player(names[1],"Resources/p2.png",667,640);
            p3 = new Player(names[2],"Resources/p3.png",640,670);
            
            P1Lbl = new JLabel(p1.getName()+" : $"+p1.getMoney());
            P2Lbl = new JLabel(p2.getName()+" : $"+p2.getMoney());
            P3Lbl = new JLabel(p3.getName()+" : $"+p3.getMoney());
            
            TitledBorder P1Title = BorderFactory.createTitledBorder(p1.getName()+" Properties");
            TitledBorder P2Title = BorderFactory.createTitledBorder(p2.getName()+" Properties");
            TitledBorder P3Title = BorderFactory.createTitledBorder(p3.getName()+" Properties");
            
            Data.add(P1Lbl); 
            Data.add(listP1);
            
            Data.add(P2Lbl); 
            Data.add(listP2);
            
            Data.add(P3Lbl); 
            Data.add(listP3);
            
            P1Lbl.setPreferredSize(new Dimension(293,15));
            P1Lbl.setFont((new Font("Arial",Font.BOLD,15)));
            P1Lbl.setForeground(Color.white);
            
            P2Lbl.setPreferredSize(new Dimension(293,15));
            P2Lbl.setFont((new Font("Arial",Font.BOLD,15)));
            P2Lbl.setForeground(Color.white);
            
            P3Lbl.setPreferredSize(new Dimension(293,15));
            P3Lbl.setFont((new Font("Arial",Font.BOLD,15)));
            P3Lbl.setForeground(Color.white);
            
           
            listP1.setBorder(P1Title);
            listP1.setPreferredSize(new Dimension(293,200));
            listP1.setFont(new Font("Arial",Font.PLAIN,12));
            listP1.setCellRenderer(new MyCellRenderer(Countries));
            
            listP2.setBorder(P2Title);
            listP2.setPreferredSize(new Dimension(293,200));
            listP2.setFont(new Font("Arial",Font.PLAIN,12));
            listP2.setCellRenderer(new MyCellRenderer(Countries));
            
            listP3.setBorder(P3Title);
            listP3.setPreferredSize(new Dimension(293,200));
            listP3.setFont(new Font("Arial",Font.PLAIN,12));
            listP3.setCellRenderer(new MyCellRenderer(Countries));
           
            Players.add(p1);
            Players.add(p2);
            Players.add(p3);  
            
            Models = new DefaultListModel[3];
            Models[0] = modelP1;
            Models[1] = modelP2;
            Models[2] = modelP3;
        }
        
        else if(n == 4)
        {
            p1 = new Player(names[0],"Resources/p1.png",640,640);
            p2 = new Player(names[1],"Resources/p2.png",667,640);
            p3 = new Player(names[2],"Resources/p3.png",640,670);
            p4 = new Player(names[3],"Resources/p4.png",667,670);
            
            P1Lbl = new JLabel(p1.getName()+" : $"+p1.getMoney());
            P2Lbl = new JLabel(p2.getName()+" : $"+p2.getMoney());
            P3Lbl = new JLabel(p3.getName()+" : $"+p3.getMoney());
            P4Lbl = new JLabel(p4.getName()+" : $"+p4.getMoney());
            
            TitledBorder P1Title = BorderFactory.createTitledBorder(p1.getName()+" Properties");
            TitledBorder P2Title = BorderFactory.createTitledBorder(p2.getName()+" Properties");
            TitledBorder P3Title = BorderFactory.createTitledBorder(p3.getName()+" Properties");
            TitledBorder P4Title = BorderFactory.createTitledBorder(p4.getName()+" Properites");
            
            Data.add(P1Lbl); 
            Data.add(listP1);
            
            Data.add(P2Lbl); 
            Data.add(listP2);
            
            Data.add(P3Lbl); 
            Data.add(listP3);
            
            Data.add(P4Lbl); 
            Data.add(listP4);
        
            P1Lbl.setPreferredSize(new Dimension(293,15));
            P1Lbl.setFont((new Font("Arial",Font.BOLD,15)));
            P1Lbl.setForeground(Color.white);
           
            P2Lbl.setPreferredSize(new Dimension(293,15));
            P2Lbl.setFont((new Font("Arial",Font.BOLD,15)));
            P2Lbl.setForeground(Color.white);
            
            P3Lbl.setPreferredSize(new Dimension(293,15));
            P3Lbl.setFont((new Font("Arial",Font.BOLD,15)));
            P3Lbl.setForeground(Color.white);
            
            P4Lbl.setPreferredSize(new Dimension(293,15));
            P4Lbl.setFont((new Font("Arial",Font.BOLD,15)));
            P4Lbl.setForeground(Color.white);
            
            listP1.setBorder(P1Title);
            listP1.setPreferredSize(new Dimension(293,150));
            listP1.setFont(new Font("Arial",Font.PLAIN,12));
            listP1.setCellRenderer(new MyCellRenderer(Countries));
            
            listP2.setBorder(P2Title);
            listP2.setPreferredSize(new Dimension(293,150));
            listP2.setFont(new Font("Arial",Font.PLAIN,12));
            listP2.setCellRenderer(new MyCellRenderer(Countries));
            
            listP3.setBorder(P3Title);
            listP3.setPreferredSize(new Dimension(293,150));
            listP3.setFont(new Font("Arial",Font.PLAIN,12));
            listP3.setCellRenderer(new MyCellRenderer(Countries));
            
            listP4.setBorder(P4Title);
            listP4.setPreferredSize(new Dimension(293,150));
            listP4.setFont(new Font("Arial",Font.PLAIN,12));
            listP4.setCellRenderer(new MyCellRenderer(Countries));
            
            Players.add(p1);
            Players.add(p2);
            Players.add(p3);
            Players.add(p4);
            
            Models = new DefaultListModel[4];
            Models[0] = modelP1;
            Models[1] = modelP2;
            Models[2] = modelP3;
            Models[3] = modelP4;
        }
        currPlayer = Players.get(current);
    }
    
    void initializeCountries()
    {
        Countries.add(new Location("GO",null));
        
        Countries.add(new Country("Times Square",60,3,1,new Color(5,76,130)));
        Countries.add(new communityChest("Community Chest"));
        Countries.add(new Country("Baltic Avenue",60,4,3,new Color(5,76,130)));
        Countries.add(new Location ("Income Tax",null));
        Countries.add(new RailRoad("Reading Railroad",new Color(255,231,204)));
        Countries.add(new Country("Oriental Avenue",100,6,6,new Color(138,90,226)));
        Countries.add(new Chance("Chance"));
        Countries.add(new Country("Vermont Avenue",100,6,8,new Color(138,90,226)));
        Countries.add(new Country("Ellis Island",120,8,9,new Color(138,90,226)));

        Countries.add(new Jail());

        Countries.add(new Country("East Village",140,10,11,new Color(203,0,125)));
        Countries.add(new waterelec("Electric Company",new Color(84,183,255)));
        Countries.add(new Country("States Avenue",140,10,13,new Color(203,0,125)));
        Countries.add(new Country("Virginia Avenue",160,12,14,new Color(203,0,125)));
        Countries.add(new RailRoad("Pennsylvania Railroad",new Color(255,231,204)));
        Countries.add(new Country("St James Place",160,14,16,new Color(252,165,0)));
        Countries.add(new communityChest("Community Chest"));
        Countries.add(new Country("Tennesee Avenue",180,14,18,new Color(252,165,0)));
        Countries.add(new Country("New York Avenue",200,16,19,new Color(252,165,0)));
        
        Countries.add(new Location("Free Parking",null));
        
        Countries.add(new Country("Kentucky Avenue",220,18,21,new Color(217,2,0)));
        Countries.add(new Chance("Chance"));
        Countries.add(new Country("Indiana Avenue",220,18,23,new Color(217,2,0)));
        Countries.add(new Country("Illinois Avenue",240,20,24,new Color(217,2,0)));
        Countries.add(new RailRoad("B&O Railroad",new Color(255,231,204)));
        Countries.add(new Country("Atlantic Avenue",260,22,26,new Color(249,215,4)));
        Countries.add(new Country("Ventor Avenue",260,22,27,new Color(249,215,4)));
        Countries.add(new waterelec("Water Works",new Color(196,255,248)));        
        Countries.add(new Country("Marvin Gardens",280,24,29,new Color(249,215,4)));
        
        Countries.add(new Location ("Go to Jail",null));
        
        Countries.add(new Country("Pacific Avenue",300,26,31,new Color(53,168,5)));      
        Countries.add(new Country("Central Park",300,26,32,new Color(53,168,5)));
        Countries.add(new communityChest("Community Chest"));
        Countries.add(new Country("Penn Avenue",320,28,34,new Color(53,168,5)));
        Countries.add(new RailRoad("Short Line",new Color(255,231,204)));
        Countries.add(new Chance("Chance"));
        Countries.add(new Country("Park Place",350,35,37,new Color(15,108,178)));
        Countries.add(new Location ("Luxury Tax",null));
        Countries.add(new Country("Wall Street",400,50,39,new Color(15,108,178)));
    }
    
    public void updateLabels()
    {
        if(!p1.hasLost())
            P1Lbl.setText(p1.getName()+" : $"+p1.getMoney());
        
        if(!p2.hasLost())
            P2Lbl.setText(p2.getName()+" : $"+p2.getMoney());
        
        if(!(p3 == null) && !p3.hasLost())
            P3Lbl.setText(p3.getName()+" : $"+p3.getMoney());
        
        if(!(p4 == null) && !p4.hasLost())
            P4Lbl.setText(p4.getName()+" : $"+p4.getMoney());
    }
      
    void updateLists()
    {
        if(currPlayer.getName().equals(p1.getName()))
        {
            if(numberOfPlayers==2)
            {
                listP1.setEnabled(true);
                listP2.setEnabled(false);
                listP2.clearSelection();
            }
              
            else if(numberOfPlayers==3)
            {
                listP1.setEnabled(true);
                listP2.setEnabled(false);
                listP3.setEnabled(false);
                listP2.clearSelection();
                listP3.clearSelection();
            }

            else if(numberOfPlayers ==4)
            {
                listP1.setEnabled(true);
                listP2.setEnabled(false);
                listP3.setEnabled(false);
                listP4.setEnabled(false);
                listP2.clearSelection();
                listP3.clearSelection();
                listP4.clearSelection();
            }
          }
          
        else if(currPlayer.getName().equals(p2.getName()))
        {
            if(numberOfPlayers==2)
            {
                listP1.setEnabled(false);
                listP2.setEnabled(true);
                listP1.clearSelection();
            }

             else if(numberOfPlayers==3)
            {
                listP1.setEnabled(false);
                listP2.setEnabled(true);
                listP3.setEnabled(false);
                listP1.clearSelection();
                listP3.clearSelection();

            }

            else if(numberOfPlayers ==4)
            {
                listP1.setEnabled(false);
                listP2.setEnabled(true);
                listP3.setEnabled(false);
                listP4.setEnabled(false);
                listP1.clearSelection();
                listP3.clearSelection();
                listP4.clearSelection();
            }
        }

        else if(!(p3 == null) && currPlayer.getName().equals(p3.getName()))
        {
            if(numberOfPlayers == 3)
            {
                listP1.setEnabled(false);
                listP2.setEnabled(false);
                listP3.setEnabled(true);
                listP1.clearSelection();
                listP2.clearSelection();
            }

            else if(numberOfPlayers == 4)
            {
                listP1.setEnabled(false);
                listP2.setEnabled(false);
                listP3.setEnabled(true);
                listP4.setEnabled(false);
                listP1.clearSelection();
                listP2.clearSelection();
                listP4.clearSelection();
            }
        }

        else if(!(p4 == null) && currPlayer.getName().equals(p4.getName()))
        {
            listP1.setEnabled(false);
            listP2.setEnabled(false);
            listP3.setEnabled(false);
            listP4.setEnabled(true);
            listP1.clearSelection();
            listP2.clearSelection();
            listP3.clearSelection();
        }
    }
    
    public void checkIfLost()
    {
        if(currPlayer.hasLost())
           {
               JOptionPane.showMessageDialog(null, "YOU LOST :(");
               if(currPlayer.getName().equals(p1.getName()))
               {
                   P1Lbl.setText(p1.getName()+" : LOST");
                   listP1.setListData(new String[]{""});
               }
               
               else if(currPlayer.getName().equals(p2.getName()))
               {
                   P2Lbl.setText(p2.getName()+" : LOST");
                   listP2.setListData(new String[]{""});
               }
               
               else if(currPlayer.getName().equals(p3.getName()))
               {
                   P3Lbl.setText(p3.getName()+" : LOST");
                   listP3.setListData(new String[]{""});
               }
               
               else if(currPlayer.getName().equals(p4.getName()))
               {
                   P4Lbl.setText(p4.getName()+" : LOST");
                   listP4.setListData(new String[]{""});
               }

               currPlayer.Kick();
               numberOfPlayers--;
               current--;
               Players.remove(currPlayer); 
               Board.setPlayers(Players);
               Board.setNumberOfPlayers(numberOfPlayers);
               Board.repaint();  
               EndTurn.doClick();
               
               if(Players.size() == 1)
               {
                   JOptionPane.showMessageDialog(null, "CONGRATULATIONS! "+Players.get(0).getName().toUpperCase()+"IS THE WINNER");
                   EndGame(0);
               }
           }
    }
    
    class InfoButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Location l = Countries.get(currPlayer.getIndex());
            Info.setText(l.toString());
            Info.setFont(new Font("Arial",Font.BOLD,14));
        }
    }
    
    class RollButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            DiceTimer.start();         
            Roll.setEnabled(false);    
            EndTurn.setEnabled(false);
        }
    }
    
    class EndButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+currPlayer.getName()+" ended their turn.");
            BuildHouse.setEnabled(false);
            BuildHotel.setEnabled(false);
            current = (current+1)%numberOfPlayers;
            currPlayer = Players.get(current);
            
            while(currPlayer.isPrisoned() && !(currPlayer.hasFreePass()) && !(currPlayer.PaidForPrison))
            {
                Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+currPlayer.getName()+" is in jail and cannot play this turn.");
                currPlayer.setPrisoned(false);
                current = (current+1)%numberOfPlayers;
                currPlayer = Players.get(current);
            }
            
            Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+currPlayer.getName()+"'s turn");
            Location l = Countries.get(currPlayer.getIndex());
                if(l instanceof Country)
                {
                    Country c = (Country) l;
                    if(c.getOwner().equals(currPlayer.getName()))
                    {
                        SellHouse.setEnabled(true);
                        SellHotel.setEnabled(true);
                    }
                    
                    else
                    {
                        SellHotel.setEnabled(false);
                        SellHouse.setEnabled(false);
                    }
                }
                
                else
                {
                    SellHotel.setEnabled(false);
                    SellHouse.setEnabled(false);
                }
            
            isFirstTurn = true;
            currPlayer.setFreePass(false);
            currPlayer.PaidForPrison=false;
            Roll.setEnabled(true);
            Buy.setEnabled(false);
            EndTurn.setEnabled(false);
            updateLists();
        }
    }
    
    class BuyButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            boolean Success = currPlayer.Buy();
            updateLabels();
            if(currPlayer.getName().equals(p1.getName()) && Success)
                  modelP1.addElement(p1.getCurrentLocation().getName());

            else if(currPlayer.getName().equals(p2.getName()) && Success)
                  modelP2.addElement(p2.getCurrentLocation().getName());

            else if(!(p3 == null) && currPlayer.getName().equals(p3.getName()) && Success)
                 modelP3.addElement(p3.getCurrentLocation().getName());
            
            else if(!(p4 == null) && currPlayer.getName().equals(p4.getName()) && Success)
                 modelP4.addElement(p4.getCurrentLocation().getName());    
            
            if (currPlayer.getCurrentLocation() instanceof Country)
            {
                Country c = (Country) currPlayer.getCurrentLocation();
                if(c.isSetComplete()&&Success)
                    BuildHouse.setEnabled(true);
            }
            
            Location l = Countries.get(currPlayer.getIndex());
                if(l instanceof Country)
                {
                    Country c = (Country) l;
                    if(c.getOwner().equals(currPlayer.getName()))
                    {
                        SellHotel.setEnabled(true);
                        SellHouse.setEnabled(true);
                    }
                    
                    else
                    {
                        SellHotel.setEnabled(false);
                        SellHouse.setEnabled(false);
                    }
                }
                
                else
                {
                    SellHotel.setEnabled(false);
                    SellHouse.setEnabled(false);
                }
        }
    }
    
    class SellButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int index = getListIndex(); 
                boolean Success = currPlayer.Sell(index);    
                updateLabels();
                if(currPlayer.getName().equals(p1.getName()) && Success)
                    modelP1.removeElementAt(index);

                else if(currPlayer.getName().equals(p2.getName()) && Success)
                    modelP2.removeElementAt(index);

                else if(!(p3 == null) && currPlayer.getName().equals(p3.getName()) && Success)
                    modelP3.removeElementAt(index);

                else if(!(p4 == null) && currPlayer.getName().equals(p4.getName()) && Success)
                    modelP4.removeElementAt(index); 
                
                Location l = Countries.get(currPlayer.getIndex());
                if(l instanceof Country)
                {
                    Country c = (Country) l;
                    if(c.getOwner().equals(currPlayer.getName()))
                    {
                        SellHotel.setEnabled(true);
                        SellHouse.setEnabled(true);
                    }
                    
                    else
                    {
                        SellHotel.setEnabled(false);
                        SellHouse.setEnabled(false);
                    }
                }
                
                else
                {
                    SellHotel.setEnabled(false);
                    SellHouse.setEnabled(false);
                }
            }
                    
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Select a country first from your list to sell!");
            }  
        }
    }
    
    public class BtnHotelSellListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Country c = (Country) currPlayer.getCurrentLocation() ;
           if(c.getnHotels() == 1)
           {
                currPlayer.addMoney(100);
                c.setnHouses(4);
                c.setnHotels(0);
                c.setRent(c.getRent()-200);
                updateLabels();
                Board.repaint();
           }
           
           else
               JOptionPane.showMessageDialog(null, "You don't own any hotels to sell!");
        }
    }
    
    public class SellHousesBtnListener implements ActionListener
    {
    @Override
    public void actionPerformed(ActionEvent e){
        
           Country c = (Country) currPlayer.getCurrentLocation() ;
           if(c.getnHouses() >= 1)
           {
                currPlayer.addMoney(100);

                c.setnHouses(c.getnHouses()-1);
                c.setRent(c.getRent()-200);
                updateLabels();
                Board.repaint();
           }
           
           else
               JOptionPane.showMessageDialog(null, "You don't own any houses to sell!");
        
        }
    }

    class motionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           if(DiceRoll >  0)
           {
                EndTurn.setEnabled(false);
                Buy.setEnabled(false);
                Sell.setEnabled(false);
                currPlayer.Move(currPlayer.isForward());
                updateLabels();
                DiceRoll--;
                Board.repaint();
                
           }
           
           else
           {
                currPlayer.setDirection(true);
                Location l = Countries.get(currPlayer.getIndex());
                if(l instanceof Country)
                {
                    Country c = (Country) l;
                    if(c.getOwner().equals(currPlayer.getName()))
                    {
                        SellHotel.setEnabled(true);
                        SellHouse.setEnabled(true);
                    }
                    
                    else
                    {
                        SellHotel.setEnabled(false);
                        SellHouse.setEnabled(false);
                    }
                }
                
                else
                {
                    SellHotel.setEnabled(false);
                    SellHouse.setEnabled(false);
                }
                
                currPlayer.setCurr(l);
                motionTimer.stop();
                Buy.setEnabled(true);
                Sell.setEnabled(true);
               
                if(Dice.getIsDouble() && isFirstTurn && !currPlayer.isPrisoned())
               {
                    Roll.setEnabled(true); 
                    InfoLog.setText(InfoLog.getText()+"\n"+currPlayer.getName()+" gets to roll one more time!");
                    isFirstTurn = false;                    
                }
               
               else
               {
                   Roll.setEnabled(false);
                   isFirstTurn = true;
               }

               if(l instanceof Country)
               {
                   Country c = (Country) l;
                   if(c.isSetComplete()&& c.getOwner().equals(currPlayer.getName()))
                   {   BuildHouse.setEnabled(true);
                       
                   }
                   
                   if(c.getnHouses()==4)
                           if((checkHotels(c.getcIndex()) || checkEvenHouses(c.getcIndex(),4)) && c.getnHotels()==0)
                            BuildHotel.setEnabled(true);
               }
               
               currPlayer.checkRent(Players,Dice);
               currPlayer.checkTaxes();
               currPlayer.checkChance(motionTimer, DiceTimer, Dice, Game.this);
               currPlayer.checkCommunityChest(motionTimer, DiceTimer, Dice, Game.this);
               currPlayer.CheckJail(motionTimer, Game.this);
               EndTurn.setEnabled(true);
               checkIfLost();
               updateLabels();                
               updateLists();

           }
        }
    }
    
    class DiceListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(Dice.getCount() < DiceConst)
            {
                Dice.Roll();
                Board.repaint();
            }
            
            else
            {
//                Scanner sc = new Scanner(System.in);
//                DiceRoll = sc.nextInt();
                
                DiceRoll = Dice.getDiceRoll();
                Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+currPlayer.getName()+" rolled "+DiceRoll);
                Dice.setCount(0);
                DiceTimer.stop();
                motionTimer.start();
                DiceTimer.stop();
            }
        }   
    }
    
    public boolean checkEvenHouses(int index,int nHouses)
    {
        Country c1;
        Country c2;
        if(index==1|index==3||index==37||index==39)
        {
            if(index==1)
            {
              c1 = (Country)Countries.get(3);
            }
            else if(index==3)
            {    c1= (Country)Countries.get(1);
            }

            else if(index==37)
            {
                c1= (Country)Countries.get(39);
            }
            else 
            {    
                c1= (Country)Countries.get(37);            
            }
            
            if(c1.getnHouses()==nHouses||c1.getnHouses()==nHouses+1)
                    return true;
            else 
                return false;
        }
         else if(index==6)
        {    
            c1= (Country)Countries.get(8);
            c2= (Country)Countries.get(9);           
        }
         else if(index==8)
        {    
            c1= (Country)Countries.get(6);
            c2= (Country)Countries.get(9);           
        }
         else if(index==6)
        {    
            c1= (Country)Countries.get(8);
            c2= (Country)Countries.get(6);           
        }
         else if(index==11)
        {    
            c1= (Country)Countries.get(13);
            c2= (Country)Countries.get(14);           
        }
         else if(index==13)
        {    
            c1= (Country)Countries.get(11);
            c2= (Country)Countries.get(14);           
        }
         else if(index==14)
        {    
            c1= (Country)Countries.get(11);
            c2= (Country)Countries.get(13);           
        }
         else if(index==16)
        {    
            c1= (Country)Countries.get(18);
            c2= (Country)Countries.get(19);           
        }
         else if(index==18)
        {    
            c1= (Country)Countries.get(16);
            c2= (Country)Countries.get(19);           
        }
         else if(index==19)
        {    
            c1= (Country)Countries.get(16);
            c2= (Country)Countries.get(18);           
        }
         else if(index==21)
        {    
            c1= (Country)Countries.get(23);
            c2= (Country)Countries.get(24);           
        }
         else if(index==23)
        {    
            c1= (Country)Countries.get(21);
            c2= (Country)Countries.get(24);           
        }
         else if(index==24)
        {    
            c1= (Country)Countries.get(21);
            c2= (Country)Countries.get(23);           
        }
         else if(index==26)
        {    
            c1= (Country)Countries.get(27);
            c2= (Country)Countries.get(29);           
        }
         else if(index==27)
        {    
            c1= (Country)Countries.get(26);
            c2= (Country)Countries.get(29);           
        }
         else if(index==29)
        {    
            c1= (Country)Countries.get(26);
            c2= (Country)Countries.get(27);           
        }
         else if(index==31)
        {    
            c1= (Country)Countries.get(32);
            c2= (Country)Countries.get(34);           
        }
         else if(index==32)
        {    
            c1= (Country)Countries.get(31);
            c2= (Country)Countries.get(34);           
        }
         else
        {    
            c1= (Country)Countries.get(31);
            c2= (Country)Countries.get(32);           
        }
        if((c1.getnHouses()==nHouses||c1.getnHouses()==nHouses+1)&&(c2.getnHouses()==nHouses||c2.getnHouses()==nHouses+1))
                return true;
     
        return false;
    }
    public boolean checkHotels(int index)
    {
        Country c1;
        Country c2;
        if(index==1|index==3||index==37||index==39)
        {
            if(index==1)
            {
              c1 = (Country)Countries.get(3);
            }
            else if(index==3)
            {    c1= (Country)Countries.get(1);
            }

            else if(index==37)
            {
                c1= (Country)Countries.get(39);
            }
            else 
            {    
                c1= (Country)Countries.get(37);            
            }
            
            if(c1.getnHotels()==1)
                    return true;
            else 
                return false;
        }
         else if(index==6)
        {    
            c1= (Country)Countries.get(8);
            c2= (Country)Countries.get(9);           
        }
         else if(index==8)
        {    
            c1= (Country)Countries.get(6);
            c2= (Country)Countries.get(9);           
        }
         else if(index==6)
        {    
            c1= (Country)Countries.get(8);
            c2= (Country)Countries.get(6);           
        }
         else if(index==11)
        {    
            c1= (Country)Countries.get(13);
            c2= (Country)Countries.get(14);           
        }
         else if(index==13)
        {    
            c1= (Country)Countries.get(11);
            c2= (Country)Countries.get(14);           
        }
         else if(index==14)
        {    
            c1= (Country)Countries.get(11);
            c2= (Country)Countries.get(13);           
        }
         else if(index==16)
        {    
            c1= (Country)Countries.get(18);
            c2= (Country)Countries.get(19);           
        }
         else if(index==18)
        {    
            c1= (Country)Countries.get(16);
            c2= (Country)Countries.get(19);           
        }
         else if(index==19)
        {    
            c1= (Country)Countries.get(16);
            c2= (Country)Countries.get(18);           
        }
         else if(index==21)
        {    
            c1= (Country)Countries.get(23);
            c2= (Country)Countries.get(24);           
        }
         else if(index==23)
        {    
            c1= (Country)Countries.get(21);
            c2= (Country)Countries.get(24);           
        }
         else if(index==24)
        {    
            c1= (Country)Countries.get(21);
            c2= (Country)Countries.get(23);           
        }
         else if(index==26)
        {    
            c1= (Country)Countries.get(27);
            c2= (Country)Countries.get(29);           
        }
         else if(index==27)
        {    
            c1= (Country)Countries.get(26);
            c2= (Country)Countries.get(29);           
        }
         else if(index==29)
        {    
            c1= (Country)Countries.get(26);
            c2= (Country)Countries.get(27);           
        }
         else if(index==31)
        {    
            c1= (Country)Countries.get(32);
            c2= (Country)Countries.get(34);           
        }
         else if(index==32)
        {    
            c1= (Country)Countries.get(31);
            c2= (Country)Countries.get(34);           
        }
         else
        {    
            c1= (Country)Countries.get(31);
            c2= (Country)Countries.get(32);           
        }
        if((c1.getnHotels()==1)||(c2.getnHotels()==1))
                return true;
     
        return false;
    }
    
    public class BtnBuildListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(currPlayer.CanBuy(200,false))
            {
                Country c = (Country) currPlayer.getCurrentLocation() ;
                try
                {
                  if(!checkEvenHouses(c.getcIndex(),c.getnHouses()))
                  throw new BuildEvenException();
                }
                catch(BuildEvenException exc)
                {
                    JOptionPane.showMessageDialog(null, exc.getMessage());
                    return;
                }            
                if (c.getnHouses()==4)
                {
                     JOptionPane.showMessageDialog(null, "You already own 4 houses which is the maximum");
                     return;
                    
                }
                c.setnHouses(c.getnHouses()+1);
                if(c.getnHouses()==4)
                    if((checkHotels(c.getcIndex()) || checkEvenHouses(c.getcIndex(),4)) && c.getnHotels()==0)
                        BuildHotel.setEnabled(true);
                 
                currPlayer.deductMoney(200);
                Game.InfoLog.setText(Game.InfoLog.getText()+"\n"+currPlayer.getName()+" built a house on "+c.getName());
                
                c.setRent(200+c.getRent());
                updateLabels();
                repaint();
            }
        }
    }
    public class BtnHotelBuildListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(currPlayer.CanBuy(200,false))
            {
                Country c = (Country) currPlayer.getCurrentLocation() ;
                if(c.getnHouses() == 4)
                {
                    c.setnHouses(0);
                    c.setnHotels(1);
                    currPlayer.deductMoney(200);
                    c.setRent(c.getRent()+200);
                    updateLabels();
                    repaint();
                }
            }
        }
    }
    public class SaveBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                File f = new File("SavedGame.data");
                f.delete();
                FileOutputStream fos = new FileOutputStream("SavedGame.data");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                saveGame(oos);
                oos.flush();
                oos.close();
                fos.close();
                JOptionPane.showMessageDialog(null, "Game has been saved!");
            }
            
            catch(Exception ex)
            {
                
            }
        }
    }
    
    public class ExitBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
            Object[] options = {"Yes" , "No"} ;
            int n = JOptionPane.showOptionDialog(Game.this, "Are you sure you want to exit!", "!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
            EndGame(n);
        }
    }
    
   public int getListIndex()
   {
        if(currPlayer.getName().equals(p1.getName()))
            return listP1.getSelectedIndex();

        else if(currPlayer.getName().equals(p2.getName()))        
            return listP2.getSelectedIndex();

        else if(!(p3 == null) && currPlayer.getName().equals(p3.getName()))
            return listP3.getSelectedIndex();

        else if(!(p4 == null) && currPlayer.getName().equals(p4.getName()))
            return listP4.getSelectedIndex();
          
        return 0;
   } 
   
   public void EndGame(int n)
   {
       if(n == 0)
       {
            new StartFrame();
            JFrame GameFrame =(JFrame) SwingUtilities.getWindowAncestor(this);
            this.setVisible(false);
       }
       
       else 
       {
            JOptionPane.getRootFrame().dispose(); 
       }
   }
}
 

