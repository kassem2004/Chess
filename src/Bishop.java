public class Bishop extends Piece{
    public Bishop(boolean color, int x, int y){
        super(color, x, y);
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

        boolean check = checkPath(board, newX, newY, this.getX(), this.getY());

        if((check) && (Math.abs(deltaX) == Math.abs(deltaY))){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPath(Piece[][] board, int newX, int newY, int oldX, int oldY) {
        int stepX = Integer.compare(newX, oldX);
        int stepY = Integer.compare(newY, oldY);

        if (Math.abs(newX - oldX) != Math.abs(newY - oldY)) {
            // Not diagonal
            return false;
        }

        for (int i = oldX + stepX, j = oldY + stepY; i != newX; i += stepX, j += stepY) {
            if (board[i][j] != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void capture(Piece eatPiece) {

    }
}
