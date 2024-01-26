import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ChessBoard extends JFrame {

    private static final int BOARD_SIZE = 8;
    private static final int CELL_SIZE = 60;

    private Piece[][] board;

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
        // Place white pawns
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[6][i] = new Pawn(false, 6, i);
        }

        // Place black pawns
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[1][i] = new Pawn(true, 1, i);
        }
    }

    private void initializeRooks(){
        board[0][0] = new Rook(true, 0, 0);
        board[0][7] = new Rook(true, 0, 7);
        board[7][0] = new Rook(false, 0, 0);
        board[7][7] = new Rook(false, 0, 7);
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

    private void initializeBoardGUI() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                JPanel cell = new JPanel();
                cell.setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.BLUE);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(cell);

                if (board[i][j] != null) {
                    String imagePath = "";
                    if(board[i][j] instanceof Pawn) {
                        imagePath = board[i][j].getColor() ? "Images/Chess_pdt60.png" : "Images/Chess_plt60.png";
                    } else if (board[i][j] instanceof Rook) {
                        imagePath = board[i][j].getColor() ? "Images/Chess_rdt60.png" : "Images/Chess_rlt60.png";
                    } else if(board[i][j] instanceof King){
                        imagePath = board[i][j].getColor() ? "Images/Chess_kdt60.png" : "Images/Chess_klt60.png";
                    } else if(board[i][j] instanceof Queen){
                        imagePath = board[i][j].getColor() ? "Images/Chess_qdt60.png" : "Images/Chess_qlt60.png";
                    } else if(board[i][j] instanceof Bishop){
                        imagePath = board[i][j].getColor() ? "Images/Chess_bdt60.png" : "Images/Chess_blt60.png";
                    } else if(board[i][j] instanceof Knight){
                        imagePath = board[i][j].getColor() ? "Images/Chess_ndt60.png" : "Images/Chess_nlt60.png";
                    }

                    try {
                        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(CELL_SIZE, CELL_SIZE, Image.SCALE_SMOOTH));
                        JLabel pawnLabel = new JLabel(icon);

                        pawnLabel.setHorizontalAlignment(JLabel.CENTER);
                        pawnLabel.setVerticalAlignment(JLabel.CENTER);
                        cell.add(pawnLabel);
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
