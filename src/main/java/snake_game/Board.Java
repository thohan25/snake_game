package snake_game;

public class Board {

  static final int ROWS = 15;
  static final int COLUMNS = 15;
  public char[][] board;
  static Snake s = new Snake();
  /*
   * ' ' = empty
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
    
    board[s.indices[0][0]][s.indices[0][1]] = 's';
    board[s.indices[1][0]][s.indices[1][1]] = 's';
    board[s.indices[2][0]][s.indices[2][1]] = 's';
  }

  public String toString() {
    String boardString = "";
    for (int i = 0; i < (((COLUMNS / 3) * 13) - ((COLUMNS / 15) * 4)); i++) {
      boardString += "-";
    }
    boardString += "\n";
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLUMNS; j++) {
        boardString += "| " + board[i][j] + " ";
      }
      boardString += "|\n";
      for (int k = 0; k < (((COLUMNS / 3) * 13) - ((COLUMNS / 15) * 4)); k++) {
        boardString += "-";
      }
      boardString += "\n";
    }
    return boardString;
  }

  public void randomizeApple() {
    int row = (int) (Math.random() * ROWS);
    int column = (int) (Math.random() * COLUMNS);
    while (board[row][column] != ' ') {
      row = (int) (Math.random() * ROWS);
      column = (int) (Math.random() * COLUMNS);
    }
    board[row][column] = 'a';
    Apple a = new Apple(row, column, board);
  }

  public void snakePositions(Snake s) {
    board[s.indices[0][0]][s.indices[0][1]] = ' ';
    s.move();
    board[s.indices[s.indices.length-1][0]][s.indices[s.indices.length-1][1]] = 's';
    
  }

  // public static void main(String[] args) {
  //   Board b = new Board();
  //   b.randomizeApple();
  //   System.out.println(b);
  //   b.snakePositions(s);
  //   System.out.println(b);
  // }

}