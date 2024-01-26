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
    public abstract boolean isValidMove(int newX, int newY);

    public abstract void capture(Piece eatPiece);

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

