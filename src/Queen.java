public class Queen extends Piece {
    public Queen(boolean color, int x, int y){
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

        if(check && ((deltaX < 0 && deltaY == 0) || (deltaY < 0 && deltaX == 0) || (deltaX > 0 && deltaY == 0) || (deltaY > 0 && deltaX == 0) || (Math.abs(deltaX) == Math.abs(deltaY)))){
            return true;
        } else {
            return false;
        }
    }

    // Combine checkPath() methods of Rook and Bishop
    public boolean checkPath(Piece[][] board, int newX, int newY, int oldX, int oldY){
        int deltaX = newX - oldX;
        int deltaY = newY - oldY;

        if (deltaX == 0) { // If its moving vertically
            int step = Integer.compare(newY, oldY);
            for (int i = oldY + step; i != newY; i += step) {
                if (board[oldX][i] != null) {
                    return false;
                }
            }
        } else if (deltaY == 0) { // If its moving horizontally
            int step = Integer.compare(newX, oldX);
            for (int i = oldX + step; i != newX; i += step) {
                if (board[i][oldY] != null) {
                    return false;
                }
            }
        } else if (Math.abs(deltaX) == Math.abs(deltaY)) { // If its moving diagonally
            int stepX = Integer.compare(newX, oldX);
            int stepY = Integer.compare(newY, oldY);
            for (int i = oldX + stepX, j = oldY + stepY; i != newX; i += stepX, j += stepY) {
                if (board[i][j] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void capture(Piece eatPiece) {

    }
}
