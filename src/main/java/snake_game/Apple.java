package snake_game;
public class Apple extends App {

    private int row;
    private int column;

    public Apple(int r, int c) {
        row = r;
        column = c;
    }

    public void eaten() {
        Snake.eatingApple(row, column);
    }
    
}