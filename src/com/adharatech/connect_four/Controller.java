package com.adharatech.connect_four;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Vector;

public class Controller
{
    private Stage myStage;
    private GraphicsContext myGc;
    private GameGrid myGameGrid;
    private Checker myRedSource;
    private Checker myBlackSource;
    private Checker myDragChecker;
    private Vector<CheckerMotion> myMotionList;

    public Controller()
    {
        myGameGrid = new GameGrid(7, 6);
        myGameGrid.setPosition(75, 10);

        myRedSource = new Checker();
        myRedSource.setColor(Checker.PieceColor.Red);
        myRedSource.setPosition(35, 35);

        myBlackSource = new Checker();
        myBlackSource.setColor(Checker.PieceColor.Black);
        myBlackSource.setPosition(35, 100);

        myDragChecker = null;
        myMotionList = new Vector<CheckerMotion>(2);

    }

    public void init(Stage aStage)
    {
        myStage = aStage;
        Group root = new Group();
        Scene s = new Scene(root);
        aStage.setScene(s);

        Canvas c = new Canvas(600, 600);
        root.getChildren().add(c);

        myGc = c.getGraphicsContext2D();

        AnimationTimer at = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                renderGame(now);
            }
        };
        at.start();

        s.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                handleMouseDragged(event);
            }
        });

        s.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                handleMouseRelease(event);
            }
        });
    }

    private void handleMouseDragged(MouseEvent ev)
    {
        double x, y;
        x = ev.getSceneX();
        y = ev.getSceneY();

        if(myDragChecker == null) {
            if (myBlackSource.contains(x, y)) {
                myDragChecker = new Checker();
                myDragChecker.setColor(Checker.PieceColor.Black);
                myDragChecker.setPosition(x, y);
            } else if (myRedSource.contains(x, y)) {
                myDragChecker = new Checker();
                myDragChecker.setColor(Checker.PieceColor.Red);
                myDragChecker.setPosition(x, y);
            }
        } else {
            myDragChecker.setPosition(x, y);
        }
    }

    private void handleMouseRelease(MouseEvent ev)
    {
        myDragChecker = null;
    }

    private void renderGame(long now)
    {
        // Clear canvas
        myGc.setFill(Color.WHITE);
        myGc.fillRect(0, 0, myStage.getWidth(), myStage.getHeight());

        // Draw source pieces
        myRedSource.render(myGc);
        myBlackSource.render(myGc);

        // Draw grid
        myGameGrid.render(myGc);

        // Draw dragged checker
        if(myDragChecker != null) {
            myDragChecker.render(myGc);
        }

        // Draw animations
    }


}
