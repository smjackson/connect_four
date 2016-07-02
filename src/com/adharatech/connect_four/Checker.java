package com.adharatech.connect_four;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by Scott on 6/27/2016.
 */
public class Checker {

    /**
     * Possible colors for a checker.
     */
    public enum PieceColor {
        Red,
        Black,
        None
    };

    protected PieceColor myColor;
    protected Circle myBounds;
    protected double myX;
    protected double myY;
    protected double myRadius;


    /**
     * Default constructor.
     */
    public Checker()
    {
        myBounds = new Circle();
        myRadius = 25f;
        myBounds.setRadius(myRadius);
        setColor(PieceColor.None);
        setPosition(0, 0);
    }

    /**
     * Sets the color of this piece.
     *
     * @param c New PieceColor to use.
     */
    public void setColor(PieceColor c)
    {
        myColor = c;
    }

    /**
     * Gets the currently set color of this piece.
     *
     * @return Current PieceColor.
     */
    public PieceColor getColor()
    {
        return myColor;
    }

    /**
     * Sets the CENTER position of the checker
     *
     * @param x Center X
     * @param y Center Y
     */
    public void setPosition(double x, double y)
    {
        myX = x;
        myY = y;
        myBounds.setCenterX(x);
        myBounds.setCenterY(y);
    }

    /**
     * Sets the radius of this piece.
     *
     * @param r new radius.
     */
    public void setRadius(double r)
    {
        myRadius = r;
    }

    /**
     * Checks whether or not a given point is within Checker.
     *
     * @param x X location
     * @param y Y location
     * @return True if location is in the checker; false otherwies
     */
    public boolean contains(double x, double y)
    {
        return myBounds.contains(x, y);
    }

    /**
     * Draws this piece to the specified GraphicsContext.
     *
     * @param gc GraphicsContext to draw to.
     */
    public void render(GraphicsContext gc)
    {
        if(myColor == PieceColor.None) {
            return; // Draw nothing
        } else if (myColor == PieceColor.Red) {
            gc.setFill(Color.RED);
        } else {
            gc.setFill(Color.BLACK);
        }

        // Calculate upper left corner position
        double x, y;
        x = myX - myRadius;
        y = myY - myRadius;

        // Draw
        gc.fillOval(x, y, myRadius * 2, myRadius * 2);

        gc.setStroke(Color.BLACK);
        gc.strokeOval(x, y, myRadius * 2, myRadius * 2);
    }
}
