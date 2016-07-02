package com.adharatech.connect_four;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Created by Scott on 6/30/2016.
 */
public class GameGrid {
    protected double myX;
    protected double myY;
    protected double myDropZoneHeight;
    protected int myColumns;
    protected int myRows;
    protected double myCellSize;
    protected Checker[][] myPieces;

    final public double DEFAULT_DROP_ZONE_HEIGHT = 125;
    final public double DEFAULT_CELL_SIZE = 60;

    /**
     * GameGrid constructor.
     *
     * @param numCols Number of columns in the game grid
     * @param numRows Number of rows in the game grid
     */
    public GameGrid(int numCols, int numRows)
    {
        myColumns = numCols;
        myRows = numRows;
        myX = myY = 0;
        myCellSize = DEFAULT_CELL_SIZE;
        myDropZoneHeight = DEFAULT_DROP_ZONE_HEIGHT;
        myPieces = new Checker[numCols][numRows];

        // Set up pieces and  locations
        for (int i = 0; i < myColumns; ++i) {
            for (int j = 0; j < myRows; ++j) {
                double x, y;
                // Compute center location
                x = myX + (i * myCellSize) + (myCellSize / 2);
                y = myY + myDropZoneHeight + (j * myCellSize) + (myCellSize / 2);

                // Store location
                myPieces[i][j] = new Checker();
                myPieces[i][j].setPosition(x, y);
            }
        }

        clearGrid();
    }

    /**
     * Clears all pieces from the grid.
     */
    public void clearGrid()
    {
        for (int i = 0; i < myColumns; ++i) {
            for (int j = 0; j < myRows; ++j) {
                setPieceColor(i, j, Checker.PieceColor.None);
            }
        }
    }

    /**
     * Sets upper-left corner of this object. This marks the start of the drop zone.
     *
     * @param x New X position
     * @param y New Y position
     */
    public void setPosition(double x, double y)
    {
        myX = x;
        myY = y;
    }

    /**
     * Gets the color of the piece at the specified coordinates.
     *
     * @param col Column to retrieve from
     * @param row Row to retrieve from
     * @return Color of the piece at the specified row and column.
     */
    public Checker.PieceColor getPieceColor(int col, int row)
    {
        return myPieces[col][row].getColor();
    }

    /**
     * Sets the color of the piece at the specified location.
     *
     * @param col Column to set.
     * @param row Row to set.
     * @param c Color to set.
     */
    public void setPieceColor(int col, int row, Checker.PieceColor c)
    {
        myPieces[col][row].setColor(c);
    }

    /**
     * Gets the number of columns in this GameGrid.
     *
     * @return Number of columns in the grid.
     */
    public int getNumColumns()
    {
        return myColumns;
    }

    /**
     * Gets the number of rows in this GameGrid.
     *
     * @return Number of rows in the grid.
     */
    public int getNumRows()
    {
        return myRows;
    }

    /**
     * Gets the current cell size.
     *
     * @return Current grid cell size, in pixels.
     */
    public double getCellSize()
    {
        return myCellSize;
    }

    /**
     * Sets a new grid cell size. Note that cells are always square so this parameter affects cell width and height
     * simultaneously.
     *
     * @param s New grid cell size in pixels.
     * @throws IllegalArgumentException
     */
    public void setCellSize(double s) throws IllegalArgumentException
    {
        if (s <= 0) {
            throw new IllegalArgumentException("Cell size must be greater than zero");
        }

        myCellSize = s;
    }

    /**
     * Draws the game grid and drop zone to the specified context.
     *
     * @param gc GraphicsContext to draw to.
     */
    public void render(GraphicsContext gc)
    {
        // Draw the drop zone
        gc.setFill(Color.GRAY);
        gc.fillRect(myX, myY, myColumns * myCellSize, myDropZoneHeight);

        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(new Font("Courier New", 48));
        gc.setFill(Color.DARKGRAY);
        gc.fillText("DROP ZONE", myX + (myColumns * myCellSize) / 2, myY + myDropZoneHeight / 2);

        // Draw vertical gridlines
        gc.setStroke(Color.GREY);
        for (int i = 0; i <= myColumns; ++i) {
            double x = myX + (i * myCellSize);
            gc.strokeLine(x, myY + myDropZoneHeight, x, myY + myDropZoneHeight + (myRows * myCellSize));
        }

        // Draw horizontal gridlines
        gc.setStroke(Color.GREY);
        for (int i = 0; i <= myRows; ++i) {
            double y = myY + myDropZoneHeight + (i * myCellSize);
            gc.strokeLine(myX, y, myX + (myColumns * myCellSize), y);
        }

        // Draw checkers
        for (int i = 0; i < myColumns; ++i) {
            for (int j = 0; j < myRows; ++j) {
                myPieces[i][j].render(gc);
            }
        }

    }
}
