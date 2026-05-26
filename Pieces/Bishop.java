package Pieces;

import Utils.Color;
import Board.Board;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color, color == Color.WHITE ? '♗' : '♝');
    }

    @Override
    public boolean isValidMove(int startR, int startC, int endR, int endC, Board board) {
        if (Math.abs(startR - endR) != Math.abs(startC - endC)) return false;

        int rowStep = Integer.compare(endR, startR);
        int colStep = Integer.compare(endC, startC);
        int currentRow = startR + rowStep;
        int currentCol = startC + colStep;

        while (currentRow != endR && currentCol != endC) {
            if (board.getPiece(currentRow, currentCol) != null) return false;
            currentRow += rowStep;
            currentCol += colStep;
        }
        return true;
    }
}