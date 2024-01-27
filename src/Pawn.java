public class Pawn extends Piece {

    private boolean hasMoved;

    public Pawn(boolean color, int x, int y) {
        super(color, x, y);
        this.hasMoved = false;
    }

    @Override
    public boolean isValidMove(Piece[][] board, int newX, int newY) {
        int deltaX = newX - this.getX();
        int deltaY = newY - this.getY();

        // Check if you are eating your own piece
        if(board[newX][newY] != null) {
            if (board[newX][newY].getColor() == this.getColor()) {
                return false;
            }
        }

        if (deltaX == -1 && deltaY == 0 && board[newX][newY] == null) {
            return true;
        } else if (!hasMoved && deltaX == -2 && deltaY == 0 && board[newX][newY] == null && board[newX + 1][newY] == null) {
            setHasMoved();
            return true;
        } else if(deltaX == -1 && (deltaY == -1 || deltaY == 1) && board[newX][newY] != null){ // Capturing piece
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void capture(Piece eatPiece) {

    }

    public void setHasMoved() {
        this.hasMoved = true;
    }
}
