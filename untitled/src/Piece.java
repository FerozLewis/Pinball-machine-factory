import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public interface Piece {
    public static final int TILESIZE = 32;
    public void draw(Graphics2D g2d);
    public int getX();
    public int getY();
    public void moveTo(int x, int y);
}
