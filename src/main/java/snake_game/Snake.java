package snake_game;

public class Snake {

    public int[][] indices; // x and y coordinates of each snake body segment, tail comes first
    public char facing; // N/S/E/W

    public Snake() {
        indices = new int[][] {{1, 7}, {2, 7}, {3, 7}};
        facing = 'E';
    }

    public void checkFacing() {
        // read arrow key input, do not allow snake to turn into itself
    }

    public int[][] Move() {
        for (int i = 0; i < indices.length-1; i++) {
            indices[i] = indices[i+1]; // moves all segments up by one
        }

        switch (facing) {
            case 'N': indices[indices.length-1] = new int[] {indices[indices.length-2][0], indices[indices.length-2][1]++};
            case 'S': indices[indices.length-1] = new int[] {indices[indices.length-2][0], indices[indices.length-2][1]--};
            case 'E': indices[indices.length-1] = new int[] {indices[indices.length-2][0]++, indices[indices.length-2][1]};
            case 'W': indices[indices.length-1] = new int[] {indices[indices.length-2][0]--, indices[indices.length-2][1]};
            break;
        } // moves the head in the direction facing

        return indices;
    }
    
}
