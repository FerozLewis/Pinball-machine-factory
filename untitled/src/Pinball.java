import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Pinball  extends JPanel {
    public static final int ZEROX = 23;
    static final int ZEROY = 7;

    private HashMap<Point, Piece> board = new HashMap<Point, Piece>();
    public void drop(Piece p, int x, int y)	{
        repaint();
        p.moveTo(x,y);
        board.put(new Point(x, y), p);
    }
    public Piece take(int x, int y)	{
        repaint();
        return board.remove(new Point(x, y));
    }

    private Image image;
    private Piece dragged = null;
    private Point mouse = null;
    private Point clicked = null;
    public void paint(Graphics g)	{
        g.drawImage(image, 0, 0, null);
        for(Map.Entry<Point, Piece> e : board.entrySet()) {
            Point pt = e.getKey();
            Piece pc = e.getValue();
            pc.draw((Graphics2D)g);
        }
        if(mouse != null && dragged != null) {
            dragged.draw((Graphics2D)g);
            System.out.println("dragged drawed");
        }
    }
    private Piece createPiece(int x, int y, int z)
    {
        return new PixelPiece(new ConcretePiece(x,y,z));
    }
    Pinball()	{
        board.put(new Point(0,2), createPiece(11,0,2));
        board.put(new Point(0,6), createPiece(0,0,6));
        board.put(new Point(1,4), createPiece(6,1,4));
        board.put(new Point(1,5), createPiece(5,1,5));
        board.put(new Point(3,7), createPiece(1,3,7));
        board.put(new Point(4,3), createPiece(6,4,3));
        board.put(new Point(4,4), createPiece(7,4,4));
        board.put(new Point(5,4), createPiece(6,5,4));
        board.put(new Point(5,6), createPiece(0,5,6));
        board.put(new Point(6,5), createPiece(0,6,5));
        board.put(new Point(7,4), createPiece(0,7,4));


        image = new ImageIcon("board3.png").getImage();
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));

        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent ev) {
                dragged = take((ev.getX()-ZEROX)/Piece.TILESIZE, (ev.getY()-ZEROY)/Piece.TILESIZE);
                mouse = ev.getPoint();
                //code here to decorate thing
                dragged = new PixelPiece(dragged, PixelPiece.generateAT(0,0));
                clicked = mouse;
            }
            public void mouseReleased(MouseEvent ev) {
                dragged = ((PixelPiece)dragged).getDecorated();
                drop(dragged, (ev.getX()-ZEROX)/Piece.TILESIZE, (ev.getY()-ZEROY)/Piece.TILESIZE);
                dragged = null;
                undo.setEnabled(true);
                clicked = null;
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent ev)	{
                mouse = ev.getPoint();
                dragged = ((PixelPiece)dragged).getDecorated(); //take out decorator
                dragged = new PixelPiece(dragged, PixelPiece.generateAT(mouse.getX() - clicked.getX(), mouse.getY() - clicked.getY()));
                System.out.println("Pos:" + (mouse.getX() - clicked.getX()) + " " + mouse.getY());
                repaint();
            }
        });
    }

    class UndoButton implements ActionListener	{
        public void actionPerformed(ActionEvent ev)
        {
            System.out.println("UNDO");
            redo.setEnabled(true);
        }
    }

    class RedoButton implements ActionListener 	{
        public void actionPerformed(ActionEvent ev) 	{
            System.out.println("REDO");
        }
    }

    private JButton undo, redo;
    public static void main(String[] args)	{
        JFrame frame = new JFrame("PINBALL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Pinball board = new Pinball();

        JToolBar bar = new JToolBar();
        board.undo = new JButton(new ImageIcon("undo.png"));
        board.redo = new JButton(new ImageIcon("redo.png"));
        board.undo.addActionListener(board.new UndoButton());
        board.redo.addActionListener(board.new RedoButton());
        board.undo.setEnabled(false);
        board.redo.setEnabled(false);
        bar.add(board.undo);
        bar.add(board.redo);

        frame.add(bar, BorderLayout.PAGE_START);
        frame.add(board);

        frame.pack();
        frame.setVisible(true);
    }
}