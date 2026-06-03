package Pieces;

import Utils.Color;
import Board.Board;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color, color == Color.WHITE ? '\u2658' : '\u265E');
    }

    @Override
    public boolean isValidMove(int startR, int startC, int endR, int endC, Board board) {
        int rowDiff = Math.abs(startR - endR);
        int colDiff = Math.abs(startC - endC);
        boolean isValidKnightL = (rowDiff * colDiff == 2);
        return isValidKnightL;
    }
}