package Main;

import Board.Board;
import Pieces.Piece;
import Utils.Color;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;   
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGUI extends JFrame {
    private JButton[][] squares = new JButton[8][8];
    private Board board;

    private int sourceRow = -1;
    private int sourceCol = -1;

    private int whiteTime = 300; 
    private int blackTime = 300; 
    private javax.swing.JLabel timerLabel;
    private javax.swing.Timer gameTimer;

    private Color currentTurn = Color.WHITE;

    public ChessGUI() {
        board = new Board();

        setTitle("Chess Game - GUI Mode");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 8));

        initializeBoardUI();
        updateBoardUI();
        setVisible(true);


        timerLabel = new javax.swing.JLabel("White time: 05:00  |  Black time: 05:00", javax.swing.JLabel.CENTER);
        timerLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));
        timerLabel.setBackground(new java.awt.Color(44, 62, 80));
        timerLabel.setForeground(java.awt.Color.WHITE);
        timerLabel.setOpaque(true);
        timerLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 10, 0));

        this.add(timerLabel, java.awt.BorderLayout.NORTH);

        gameTimer = new javax.swing.Timer(1000, e -> {
            if (currentTurn == Utils.Color.WHITE) {
                whiteTime--;
                if (whiteTime <= 0) {
                    gameTimer.stop();
                    javax.swing.JOptionPane.showMessageDialog(this, "White time is up! Black wins. ⏳");
                }
            } else {
                blackTime--;
                if (blackTime <= 0) {
                    gameTimer.stop();
                    javax.swing.JOptionPane.showMessageDialog(this, "Black time is up! White wins. ⏳");
                }
            }
            updateTimerLabel(); 
        });

        gameTimer.start();
    }

    private void initializeBoardUI() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();

                button.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 32));
                button.setFocusPainted(false);
                button.setMargin(new Insets(0, 0, 0, 0));

                final int r = row;
                final int c = col;

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleSquareClick(r, c);
                    }
                });

                squares[row][col] = button;
                add(button);
            }
        }
    }

    private void handleSquareClick(int row, int col) {
    if (sourceRow == -1) {

        Piece p = board.getPiece(row, col);
        if (p != null && p.getColor() == currentTurn) {
            sourceRow = row;
            sourceCol = col;
            
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    Piece target = board.getPiece(r, c);
                    if (p.isValidMove(sourceRow, sourceCol, r, c, board) && (target == null || target.getColor() != p.getColor()) && 
                    !board.wouldMoveLeaveKingInCheck(sourceRow, sourceCol, r, c, currentTurn)) {

                        squares[r][c].setBackground(new java.awt.Color(186, 220, 181)); 
                    }
                }
            }
        }
    } else {

        Piece selectedPiece = board.getPiece(sourceRow, sourceCol);
        Piece targetPiece = board.getPiece(row, col); 
        
        if (selectedPiece.isValidMove(sourceRow, sourceCol, row, col, board) && (targetPiece == null || targetPiece.getColor() != selectedPiece.getColor()) && 
        !board.wouldMoveLeaveKingInCheck(sourceRow, sourceCol, row, col, currentTurn)) {

            if (selectedPiece instanceof Pawn && Math.abs(sourceCol - col) == 1 && targetPiece == null) {
                board.setPiece(sourceRow, col, null);
            }
            

            board.setPiece(row, col, selectedPiece);
            board.setPiece(sourceRow, sourceCol, null);

            selectedPiece.setMoved(true);

            if (selectedPiece instanceof King && Math.abs(sourceCol - col) == 2) {
                if (col == 6) {
                    Piece rook = board.getPiece(row, 7);
                    board.setPiece(row, 5, rook);
                    board.setPiece(row, 7, null);
                    if (rook != null) rook.setMoved(true);
                } 
                else if (col == 2) {
                    Piece rook = board.getPiece(row, 0);
                    board.setPiece(row, 3, rook);
                    board.setPiece(row, 0, null);
                    if (rook != null) rook.setMoved(true);
                }
            }

            board.recordLastMove(sourceRow, sourceCol, row, col);

            currentTurn = (currentTurn == Utils.Color.WHITE) ? Utils.Color.BLACK : Utils.Color.WHITE;

            if (!hasAnyLegalMoves(currentTurn)) {

                if (gameTimer != null) {
                    gameTimer.stop();
                }
                if (board.isInCheck(currentTurn)) {
                    String winner = (currentTurn == Utils.Color.WHITE) ? "Black" : "White";
                    JOptionPane.showMessageDialog(this, "Checkmate! Player " + winner + " winner! 👑");
                } else {
                    JOptionPane.showMessageDialog(this, "Game ended in a draw (Stalemate)! The match ended in a tie. 🤝");
                }
            }
            

            if (selectedPiece instanceof Pawn) {
                if ((selectedPiece.getColor() == Color.WHITE && row == 0) || 
                    (selectedPiece.getColor() == Color.BLACK && row == 7)) {
                    

                        String[] options = {"Queen", "Rook", "Bishop", "Knight"};
                    

                        int choice = JOptionPane.showOptionDialog(
                        this,
                        "Your pawn has reached the last rank! Which piece would you like to promote to?",
                        "Promote Pawn",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0] 
                    );
                    
                    Piece newPiece;

                    switch (choice) {
                        case 1:
                            newPiece = new Rook(selectedPiece.getColor());
                            break;
                        case 2:
                            newPiece = new Bishop(selectedPiece.getColor());
                            break;
                        case 3:
                            newPiece = new Knight(selectedPiece.getColor());
                            break;
                        case 0:
                        default:
                            newPiece = new Queen(selectedPiece.getColor());
                            break;
                    }
                    

                    board.setPiece(row, col, newPiece);
                }
            }
            

            currentTurn = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
        }
        
        sourceRow = -1;
        sourceCol = -1;
        updateBoardUI();
    }
}

    private void updateBoardUI() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(new java.awt.Color(240, 217, 181));
                } else {
                    squares[row][col].setBackground(new java.awt.Color(181, 136, 99));
                }

                Piece p = board.getPiece(row, col);
                if (p != null) {

                    squares[row][col].setText(String.valueOf(p.getSymbol()));

                    if (p.getColor() == Color.WHITE) {
                        squares[row][col].setForeground(java.awt.Color.WHITE);
                    } else {
                        squares[row][col].setForeground(java.awt.Color.BLACK);
                    }
                } else {
                    squares[row][col].setText("");
                }
            }
        }
    }

    private boolean hasAnyLegalMoves(Utils.Color color) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board.getPiece(r, c);
                if (p != null && p.getColor() == color) {
                    for (int tr = 0; tr < 8; tr++) {
                        for (int tc = 0; tc < 8; tc++) {
                            Piece target = board.getPiece(tr, tc);
                            if (p.isValidMove(r, c, tr, tc, board) && 
                                (target == null || target.getColor() != color) &&
                                !board.wouldMoveLeaveKingInCheck(r, c, tr, tc, color)) {
                                return true; 
                            }
                        }
                    }
                }
            }
        }
        return false; 
    }

    private void updateTimerLabel() {
        int wMin = whiteTime / 60;
        int wSec = whiteTime % 60;
        int bMin = blackTime / 60;
        int bSec = blackTime % 60;
        
        String timeText = String.format("White time: %02d:%02d  |  Black time: %02d:%02d", wMin, wSec, bMin, bSec);
        timerLabel.setText(timeText);
    }
}