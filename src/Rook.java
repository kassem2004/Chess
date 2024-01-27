public class Rook extends Piece{
    public Rook(boolean color, int x, int y){
        super(color, x, y);
    }
    @Override
    public boolean isValidMove(int newX, int newY) {
        int deltaX = newX - this.getX();
        int deltaY = newY - this.getY();
        if((deltaX < 0 && deltaY == 0) || (deltaY < 0 && deltaX == 0) || (deltaX > 0 && deltaY == 0) || (deltaY > 0 && deltaX == 0)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void capture(Piece eatPiece) {

    }
}
