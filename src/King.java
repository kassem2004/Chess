public class King extends Piece {
    public King(boolean color, int x, int y){
        super(color, x, y);
    }
    @Override
    public boolean isValidMove(int newX, int newY) {
        int deltaX = Math.abs(newX - this.getX());
        int deltaY = Math.abs(newY - this.getY());

        return (deltaX <= 1 && deltaY <= 1);
    }

    @Override
    public void capture(Piece eatPiece) {

    }
}
