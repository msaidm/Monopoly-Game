package monopoly;
import java.awt.*;
import java.io.Serializable;
import javax.swing.*;

public class Tile extends JLabel implements Serializable
{
    private ImageIcon Img;
    private int height;
    private int width;
    private int x;
    private int y;
    
    public Tile(String ImgPath, int x, int y , int width , int height)
    {
        ImageIcon Icon = new ImageIcon(ImgPath);
        Image ResizedTile = Icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        Img = new ImageIcon(ResizedTile);
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.setBounds(x,y,width,height);
        setIcon(Img);
    }
}
