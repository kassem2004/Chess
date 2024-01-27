public class Rook extends Piece{
    public Rook(boolean color, int x, int y){
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

        if ((check) && ((deltaX < 0 && deltaY == 0) || (deltaY < 0 && deltaX == 0) || (deltaX > 0 && deltaY == 0) || (deltaY > 0 && deltaX == 0))) {
            return true;
        } else {
            return false;
        }

    }

    // To ensure the rook is not going over a piece
    public boolean checkPath(Piece[][] board, int newX, int newY, int oldX, int oldY) {
        System.out.println("Oldx " + oldX);
        System.out.println("Oldy " + oldY);
        System.out.println("Newx " + newX);
        System.out.println("Newy " + newY);
        if (newX == oldX) { // If it is moving horizontally
            int step = Integer.compare(newY, oldY); // Check if it's moving left or right
            System.out.println(step);
            for (int i = oldY + step; i != newY; i += step) {
                if (board[oldX][i] != null) {
                    return false;
                }
            }
        }

        if (newY == oldY) { // If it is moving vertically
            int step = Integer.compare(newX, oldX);
            System.out.println(step + "v");
            for (int i = oldX + step; i != newX; i += step) {
                if (board[i][oldY] != null) {
                    return false;
                }
            }
        }
        System.out.println("haha");
        return true;
    }



    @Override
    public void capture(Piece eatPiece) {

    }
}
