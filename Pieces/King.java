package Pieces;

import Utils.Color;
import Board.Board;

public class King extends Piece {
    public King(Color color) {
        super(color, color == Color.WHITE ? 'K' : 'k');
    }

    @Override
    public boolean isValidMove(int startR, int startC, int endR, int endC, Board board) {
        int rowDiff = Math.abs(startR - endR);
        int colDiff = Math.abs(startC - endC);

        int maxStep = Math.max(rowDiff, colDiff);
        if (startR == endR && startC == endC) return false;

        return maxStep == 1;
    }
}