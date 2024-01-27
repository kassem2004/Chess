public class Knight extends Piece{
    public Knight(boolean color, int x, int y){
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

        if((deltaX == -2 && (deltaY == -1 || deltaY == 1)) || (deltaX == 2 && (deltaY == -1 || deltaY == 1)) || (deltaY == -2 && (deltaX == -1 || deltaX == 1)) || (deltaY == 2 && (deltaX == -1 || deltaX == 1))){
            return true;
        } else {
            return false;
        }
    }
}
