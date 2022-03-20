import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class ConcretePiece implements Piece {
    public static final int TILESIZE = 32;
    private static Image image = new ImageIcon("pieces4.png").getImage();

    private int index, x, y;
    public ConcretePiece(int idx, int xx, int yy)	{ index = idx; x=xx; y=yy;  }
    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, x+1, y+1,
                index*TILESIZE, 0, (index+1)*TILESIZE, TILESIZE, null);
    }
    public int getX() { return x; }
    public int getY() {return y; }
    public void moveTo(int xx, int yy) { x=xx; y=yy; }
}