package Main;

import Board.Board;
import Pieces.Piece;
import Utils.Color; 

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
                button.setFont(new Font("Arial", Font.BOLD, 36)); 
                button.setFocusPainted(false);


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

                squares[row][col].setBackground(java.awt.Color.YELLOW);
            }
        } else {

            Piece selectedPiece = board.getPiece(sourceRow, sourceCol);
            

            if (selectedPiece.isValidMove(sourceRow, sourceCol, row, col, board)) {
                // جابجایی مهره در آرایه منطقی صفحه
                board.setPiece(row, col, selectedPiece);
                board.setPiece(sourceRow, sourceCol, null);
                

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

                    if(p.getColor() == Color.WHITE) {
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
