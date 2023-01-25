public class Board {

    static final int ROWS = 16;
    static final int COLUMNS = 16;
    public char[][] board;
    /* ' ' = empty
     * 's' = snake
     * 'a' = apple
     * 'e' = snake has just eaten apple
     */

    public Board() {
        board = new char[ROWS][COLUMNS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
              board[i][j] = ' ';
            }
          }
    }

    public String toString() {
        String boardString = "";
        for (int i = 0; i < ((COLUMNS / 2) * 9); i++) {
            boardString += "_";
        }
        for (int i = 0; i < ROWS; i++) {
          for (int j = 0; j < COLUMNS; j++) {
            boardString += "| " + board[i][j] + " ";
          }
          boardString += "|\n";
        }
        for (int i = 0; i < ((COLUMNS / 2) * 9); i++) {
          boardString += "-";
        }
        return boardString;
    }

    public void randomizeApple() {
      int row = (int)(Math.random() * ROWS);
      int column = (int)(Math.random() * COLUMNS);
      while (board[row][column] == 's') {
        row = (int)(Math.random() * ROWS);
        column = (int)(Math.random() * COLUMNS);
      }
      board[row][column] = 'a';
      Apple a = new Apple(row, column, board);
    }
    
}