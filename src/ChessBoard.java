import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessBoard extends JFrame {

    private static final int BOARD_SIZE = 8;
    private static final int CELL_SIZE = 60;

    private Piece[][] board;
    private Piece selectedPiece;

    public ChessBoard() {
        // Initialize the board and set layout
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        // Add all pieces to board
        initializeAllPieces();

        // Create and set up the board GUI
        initializeBoardGUI();

        // Set up the JFrame
        setTitle("Chess Board");
        setSize(BOARD_SIZE * CELL_SIZE, BOARD_SIZE * CELL_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializePawns() {
        // White pawns
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[6][i] = new Pawn(false, 6, i);
        }

        // Black pawns
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[1][i] = new Pawn(true, 1, i);
        }
    }

    private void initializeRooks(){
        board[0][0] = new Rook(true, 0, 0);
        board[0][7] = new Rook(true, 0, 7);
        board[7][0] = new Rook(false, 7, 0);
        board[7][7] = new Rook(false, 7, 7);
    }

    private void initializeKnights() {
        board[0][1] = new Knight(true, 0, 1);
        board[0][6] = new Knight(true, 0, 6);
        board[7][1] = new Knight(false, 7, 1);
        board[7][6] = new Knight(false, 7, 6);
    }

    private void initializeBishops() {
        board[0][2] = new Bishop(true, 0, 2);
        board[0][5] = new Bishop(true, 0, 5);
        board[7][2] = new Bishop(false, 7, 2);
        board[7][5] = new Bishop(false, 7, 5);
    }

    private void initializeQueens() {
        board[0][3] = new Queen(true, 0, 3);
        board[7][3] = new Queen(false, 7, 3);
    }

    private void initializeKings() {
        board[0][4] = new King(true, 0, 4);
        board[7][4] = new King(false, 7, 4);
    }

    private void initializeAllPieces() {
        initializePawns();
        initializeRooks();
        initializeKnights();
        initializeBishops();
        initializeQueens();
        initializeKings();
    }

    private String getImagePathForPiece(Piece piece) {
        if (piece instanceof Pawn) {
            return piece.getColor() ? "Images/Chess_pdt60.png" : "Images/Chess_plt60.png";
        } else if (piece instanceof Rook) {
            return piece.getColor() ? "Images/Chess_rdt60.png" : "Images/Chess_rlt60.png";
        } else if (piece instanceof King) {
            return piece.getColor() ? "Images/Chess_kdt60.png" : "Images/Chess_klt60.png";
        } else if (piece instanceof Queen) {
            return piece.getColor() ? "Images/Chess_qdt60.png" : "Images/Chess_qlt60.png";
        } else if (piece instanceof Bishop) {
            return piece.getColor() ? "Images/Chess_bdt60.png" : "Images/Chess_blt60.png";
        } else if (piece instanceof Knight) {
            return piece.getColor() ? "Images/Chess_ndt60.png" : "Images/Chess_nlt60.png";
        }
        return "";
    }

    private class CellMouseListener extends MouseAdapter {
        private final int row;
        private final int col;

        CellMouseListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            handleCellClick(row, col);
        }
    }

    private void handleCellClick(int row, int col) {
        if (selectedPiece == null) {
            // If no piece is selected, select the piece at the clicked position
            selectedPiece = board[row][col];
            if (selectedPiece != null) {
                System.out.println("Selected piece: " + selectedPiece.getClass().getSimpleName() + " at position (" + row + ", " + col + ")");
            } else {
                System.out.println("No piece selected at position (" + row + ", " + col + ")");
            }
        } else {
            // If a piece is already selected, check if the destination is a valid move
            if (selectedPiece.isValidMove(board, row, col)) {
                // Perform the move and update the board
                board[selectedPiece.getX()][selectedPiece.getY()] = null; // Clear the source position
                board[row][col] = selectedPiece; // Move the piece to the destination
                selectedPiece.setX(row); // Update the piece's position
                selectedPiece.setY(col);

                refreshBoardGUI();
            } else {
                System.out.println("Invalid move for " + selectedPiece.getClass().getSimpleName() + " to (" + row + ", " + col + ")");
            }

            selectedPiece = null;
        }
    }

    private void flipBoard(){
        for(int i = 0; i < BOARD_SIZE/2; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                Piece tmp = board[i][j];
                board[i][j] = board[(BOARD_SIZE - 1) - i][j];
                board[(BOARD_SIZE - 1) - i][j] = tmp;

                // Update the coordinate attributes of the pieces
                if (board[i][j] != null) {
                    board[i][j].setX(i);
                    board[i][j].setY(j);
                }
                if (board[(BOARD_SIZE - 1) - i][j] != null) {
                    board[(BOARD_SIZE - 1) - i][j].setX((BOARD_SIZE - 1) - i);
                    board[(BOARD_SIZE - 1) - i][j].setY(j);
                }
            }
        }
    }

    private void refreshBoardGUI() {
        getContentPane().removeAll();
        flipBoard();
        initializeBoardGUI();
        revalidate();
        repaint();
    }

    private void initializeBoardGUI() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                JPanel cell = new JPanel();
                cell.setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.BLUE);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                cell.addMouseListener(new CellMouseListener(i, j));

                add(cell);

                if (board[i][j] != null) {
                    String imagePath = getImagePathForPiece(board[i][j]);

                    try {
                        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(CELL_SIZE, CELL_SIZE, Image.SCALE_SMOOTH));
                        JLabel pieceLabel = new JLabel(icon);

                        pieceLabel.setHorizontalAlignment(JLabel.CENTER);
                        pieceLabel.setVerticalAlignment(JLabel.CENTER);
                        cell.add(pieceLabel);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("Error loading image: " + imagePath);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChessBoard());
    }
}
