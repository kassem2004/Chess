public class Bishop extends Piece{
    public Bishop(boolean color, int x, int y){
        super(color, x, y);
    }
    @Override
    public boolean isValidMove(int newX, int newY) {
        int deltaX = newX - this.getX();
        int deltaY = newY - this.getY();
        if(Math.abs(deltaX) == Math.abs(deltaY)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void capture(Piece eatPiece) {

    }
}
