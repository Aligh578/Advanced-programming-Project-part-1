package Main;

import Board.Board;
import Pieces.Piece;
import Utils.Color;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;   
import Pieces.Bishop; 
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
                    if (p.isValidMove(sourceRow, sourceCol, r, c, board) && (target == null || target.getColor() != p.getColor())) {
                        squares[r][c].setBackground(new java.awt.Color(186, 220, 181)); 
                    }
                }
            }
        }
    } else {

        Piece selectedPiece = board.getPiece(sourceRow, sourceCol);
        Piece targetPiece = board.getPiece(row, col); 
        
        if (selectedPiece.isValidMove(sourceRow, sourceCol, row, col, board) && (targetPiece == null || targetPiece.getColor() != selectedPiece.getColor())) {

            if (selectedPiece instanceof Pawn && Math.abs(sourceCol - col) == 1 && targetPiece == null) {
                board.setPiece(sourceRow, col, null);
            }
            

            board.setPiece(row, col, selectedPiece);
            board.setPiece(sourceRow, sourceCol, null);

            board.recordLastMove(sourceRow, sourceCol, row, col);

            currentTurn = (currentTurn == Utils.Color.WHITE) ? Utils.Color.BLACK : Utils.Color.WHITE;
            

            if (selectedPiece instanceof Pawn) {
                if ((selectedPiece.getColor() == Color.WHITE && row == 0) || 
                    (selectedPiece.getColor() == Color.BLACK && row == 7)) {
                    

                        String[] options = {"وزیر (Queen)", "رخ (Rook)", "فیل (Bishop)", "اسب (Knight)"};
                    

                        int choice = JOptionPane.showOptionDialog(
                        this,
                        "سرباز شما به ردیف آخر رسید! به کدام مهره ترفیع یابد؟",
                        "انتخاب مهره ترفیع",
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
}