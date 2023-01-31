package snake_game;

public class Snake {

    private char[][] board;
    private int length = 3;
    private int[][] position = {{8, 3}, {8, 2}, {8, 1}};

    public Snake(char[][] b) {
        board = b;
    }

    private void move() {
        // int[][] location = findHeadTail();
        // if (left) {
            if (position[0][1] == 0) {
                App.gameOver();
            }
            board[position[0][0]][position[0][1] - 1] = 'h';
            for (int i = 1; i < position.length - 1; i++) {
                board[position[i][0]][position[i][1]] = board[position[i+1][0]][position[i+1][1]];
                position[i][0] = position[i+1][0];
                position[i][1] = position[i+1][1];
            }
        // }
    }

    private void eatingApple() {
        length++;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'e') {
                    board[i][j] = 'h';
                }
            }
        }
    }

    // private int[][] findHeadTail() {
    //     int[][] location = new int[2][2];
    //     for (int i = 0; i < board.length; i++) {
    //         for (int j = 0; j < board[i].length; j++) {
    //             if (board[i][j] == 'h') {
    //                 location[0][0] = i;
    //                 location[0][1] = j;
    //             } else if (board[i][j] == 't') {
    //                 location[1][0] = i;
    //                 location[1][1] = j;
    //             }
    //         }
    //     }
    //     return location;
    // }
}