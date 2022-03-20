import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class PixelPiece extends DecoratorPiece
{
    public static AffineTransform generateAT(double x, double y)
    {
        AffineTransform at = new AffineTransform();
        at.translate(x,y);
        return at;
    }
    private AffineTransform tr = null;
    public PixelPiece(Piece decorated)
    {
        super(decorated);
    }

    public PixelPiece(Piece decorated, AffineTransform tr)
    {
        super(decorated);
        this.tr = tr;
    }
    @Override
    public void draw(Graphics2D g)
    {
        AffineTransform backup = g.getTransform();
        if(tr == null)
        {
            tr = new AffineTransform();
            tr.translate(Pinball.ZEROX, Pinball.ZEROY);
            tr.scale(Piece.TILESIZE, Piece.TILESIZE);
        }
        g.setTransform(tr);
        decorated.draw(g);
        g.setTransform(backup);

    }
    public int getX(){ return decorated.getX();}
    public int getY(){ return decorated.getY();}
    public void moveTo(int x, int y){decorated.moveTo(x,y);}
    public Piece getDecorated(){ return this.decorated; }
}
