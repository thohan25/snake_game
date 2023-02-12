package snake_game;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Snake {

    public int[][] indices; // x and y coordinates of each snake body segment, tail comes first
    public char facing; // N/S/E/W
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

    AnimationTimer timer = new AnimationTimer() {
        public void handle(long timestamp) {
            frameCounter++;
            App app = new App();
            facing = app.checkFacing();

            switch (facing) {
                case 'N':
                    
                    break;
                case 'S':
                    
                    break;
                case 'E':
                    
                    break;
                case 'W':
                    
                    break;
                default:
                    break;
                }

        }
    };

    public void handleMovement() {

    }

    public int[][] move() {
        for (int i = 0; i < indices.length-1; i++) {
            indices[i] = indices[i+1]; // moves all segments up by one
        }

        switch (facing) {
            case 'N': indices[indices.length-1] = new int[] {indices[indices.length-2][0]--, indices[indices.length-2][1]};
            case 'S': indices[indices.length-1] = new int[] {indices[indices.length-2][0]++, indices[indices.length-2][1]};
            case 'E': indices[indices.length-1] = new int[] {indices[indices.length-2][0], indices[indices.length-2][1]++};
            case 'W': indices[indices.length-1] = new int[] {indices[indices.length-2][0], indices[indices.length-2][1]--};
            break;
        } // moves the head in the direction facing

        // Board.snakePositions(this);
        return indices;
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