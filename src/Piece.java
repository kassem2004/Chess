public abstract class Piece {
    private boolean isAlive;
    private boolean color; // 0 represents white and 1 represents black
    private int x; // x-coordinate on the chessboard
    private int y; // y-coordinate on the chessboard

    public Piece(boolean color, int x, int y) {
        this.isAlive = true;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Method to check if the piece can move to a new position
    public abstract boolean isValidMove(Piece[][] board, int newX, int newY);

    public boolean discoveredCheck(Piece[][] board, int newX, int newY) {
        int kingX = 0;
        int kingY = 0;

        // Find where the king is
        for(int i = 0; i < 8; i ++) {
            for(int j = 0; j < 8; j++) {
                if (board[i][j] instanceof King && board[i][j].getColor() == this.getColor()) {
                    kingX = i;
                    kingY = j;
                    i = 8;
                    j = 8;
                }
            }
        }

        // Check if any of the pieces have a valid move to eat the king
        for(int i = 0; i < 8; i ++) {
            for(int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getColor() != this.getColor()) {
                    if(board[i][j].isValidMove(board, kingX, kingY)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean getColor() {
        return color;
    }
}

