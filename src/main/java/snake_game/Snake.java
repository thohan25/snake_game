package snake_game;
import java.util.Arrays;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Snake extends App {

    public int[][] indices; // x and y coordinates of each snake body segment, head comes first
    public Rectangle[] segments;
    final int BOARD_SIZE = 15;

    public int frameCounter = 0;
    private static char trueDirection = 'E';

    public Snake() {
        indices = new int[][] {{7, 3}, {7, 2}, {7, 1}};
        segments = new Rectangle[3];
        segments[0] = new Rectangle(45, 45);
        segments[0].setFill(Color.BLUE);
        segments[1] = new Rectangle(45, 45);
        segments[1].setFill(Color.BLUE);
        segments[2] = new Rectangle(45, 45);
        segments[2].setFill(Color.BLUE);
    }

    public void update() {
        new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                frameCounter++;

                if (frameCounter == 60) {
                    int[] tail = move();
                    snakeMovement(indices, segments, tail);
                    frameCounter = 0;
                }
            }
        }.start();
    }

    public int[] move() {

        int[] tail = new int[]{indices[indices.length-1][0], indices[indices.length-1][1]};

        for (int i = indices.length-1; i > 0; i--) {
            indices[i][0] = indices[i-1][0]; // moves all segments up by one
            indices[i][1] = indices[i-1][1];
        }
        
        if (keyPress == 'N' && trueDirection == 'S') {
            keyPress = 'S';  
        } else if (keyPress == 'S' && trueDirection == 'N') {
            keyPress = 'N';  
        } else if (keyPress == 'E' && trueDirection == 'W') {
            keyPress = 'W';    
        } else if (keyPress == 'W' && trueDirection == 'E') {
            keyPress = 'E';   
        }

        if (keyPress == 'N') {
            indices[0][0]--;
            trueDirection = 'N';    
        } else if (keyPress == 'S') {
            indices[0][0]++;
            trueDirection = 'S';  
        } else if (keyPress == 'E') {
            indices[0][1]++;      
            trueDirection = 'E';    
        } else if (keyPress == 'W') {
            indices[0][1]--;  
            trueDirection = 'W';   
        }
        
        return tail;
    }

    public void eatingApple(int[] tail) {
        System.out.println("called");
        int[][] newIndices = new int[indices.length+1][2];
        int counter = 0;
        for (int[] i : indices) {
            newIndices[counter] = i;
            counter++;
        }
        newIndices[newIndices.length-1][0] = tail[0];
        newIndices[newIndices.length-1][1] = tail[1];
        indices = newIndices;

        Rectangle[] newSegments = new Rectangle[segments.length+1];
        counter = 0;
        for (Rectangle r : segments) {
            newSegments[counter] = r;
            counter++;
        }
        Rectangle builder = new Rectangle(45, 45);
        builder.setFill(Color.BLUE);
        newSegments[newSegments.length-1] = builder;
        segments = newSegments;
    }
}