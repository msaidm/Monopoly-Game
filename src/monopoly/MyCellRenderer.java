package monopoly;

import javax.swing.DefaultListCellRenderer;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

 public class MyCellRenderer extends DefaultListCellRenderer
{
    private ArrayList<Location> Countries;
     
    public MyCellRenderer(ArrayList<Location> Countries)
    {
        this.Countries = Countries;
    }
    
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
      super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
      String s = (String)value;
      Color bg = Location.getTileColor(s,Countries);
      setBackground(bg);
      setOpaque(true);
      setEnabled(true);
      setForeground(Color.black);
      setFont((new Font("Arial",Font.BOLD,13)));
      if(isSelected)
          setBackground(Color.LIGHT_GRAY);
        
      return this;  
    }
}