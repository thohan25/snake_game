public class Board {

    static final int ROWS = 16;
    static final int COLUMNS = 16;
    public char[][] board;

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

    public String randomizeApple() {
      String location = "[][]";
      int row = (int)(Math.random() * ROWS); // location 1
      int column = (int)(Math.random() * COLUMNS); // location 4
      // location = location.substring(0, 1) + row + location.substring(1, 3) + column + location.substring(3);
      Apple a = new Apple(row, column);

      return location;
    }
    
}