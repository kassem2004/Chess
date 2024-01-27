public class King extends Piece {
    public King(boolean color, int x, int y){
        super(color, x, y);
    }
    @Override
    public boolean isValidMove(Piece[][] board, int newX, int newY) {
        int deltaX = Math.abs(newX - this.getX());
        int deltaY = Math.abs(newY - this.getY());

        // Check if you are eating your own piece
        if(board[newX][newY] != null) {
            if (board[newX][newY].getColor() == this.getColor()) {
                return false;
            }
        }

        return (deltaX <= 1 && deltaY <= 1);
    }

    @Override
    public void capture(Piece eatPiece) {

    }
}
