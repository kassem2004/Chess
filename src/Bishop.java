public class Bishop extends Piece{
    public Bishop(boolean color, int x, int y){
        super(color, x, y);
    }
    @Override
    public boolean isValidMove(int newX, int newY) {
        return false;
    }

    @Override
    public void capture(Piece eatPiece) {

    }
}
