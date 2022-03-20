import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public abstract class DecoratorPiece implements Piece
{
    Piece decorated;
    DecoratorPiece(Piece decorated)
    {
        this.decorated = decorated;
    }
}
