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

    public int[][] indices; // x and y coordinates of each snake body segment, tail comes first
        public Rectangle[] segments;
    final int BOARD_SIZE = 15;

    public int frameCounter = 0;

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

                if (frameCounter == 100) {
                    move();
                    snakeMovement(indices, segments);
                    frameCounter = 0;
                }
            }
        }.start();
    }

    public void move() {
        for (int i = indices.length-1; i > 0; i--) {
            indices[i][0] = indices[i-1][0]; // moves all segments up by one
            indices[i][1] = indices[i-1][1];
        }
        System.out.println(keyPress);
        if (keyPress == 'N') {
            indices[0][0]--;    
        } else if (keyPress == 'S') {
            indices[0][0]++;
        } else if (keyPress == 'E') {
            indices[0][1]++;        
        } else if (keyPress == 'W') {
            indices[0][1]--;    
        }
        
        // moves the head in the direction facing

        // Board.snakePositions(this);
    }

    public void eatingApple(int row, int column) {
        int[][] newIndices = new int[indices.length+1][];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                newIndices[i][j] = indices[i][j];
            }
        }
        newIndices[indices.length-1][0] = row;
        newIndices[indices.length-1][1] = column;
    }
}
