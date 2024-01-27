public class Pawn extends Piece {

    private boolean hasMoved; // Track whether the pawn has moved

    public Pawn(boolean color, int x, int y) {
        super(color, x, y);
        this.hasMoved = false;
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        int deltaX = newX - this.getX();
        int deltaY = newY - this.getY();

        if (deltaX == -1 && deltaY == 0) {
            return true;
        } else if (!hasMoved && deltaX == -2 && deltaY == 0) {
            setHasMoved();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void capture(Piece eatPiece) {
        // Implement capture logic if needed
    }

    // Additional method to update the hasMoved flag
    public void setHasMoved() {
        this.hasMoved = true;
    }
}
