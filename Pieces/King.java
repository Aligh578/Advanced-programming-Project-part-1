package Pieces;

import Utils.Color;
import Board.Board;

public class King extends Piece {
    public King(Color color) {
        super(color, color == Color.WHITE ? '♔' : '♚');
    }

    @Override
    public boolean isValidMove(int startR, int startC, int endR, int endC, Board board) {
        int rowDiff = Math.abs(startR - endR);
        int colDiff = Math.abs(startC - endC);
        return rowDiff <= 1 && colDiff <= 1;
    }
}