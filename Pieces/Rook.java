package Pieces;

import Utils.Color;
import Board.Board;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color, color == Color.WHITE ? '\u2656' : '\u265C');
    }

    @Override
    public boolean isValidMove(int startR, int startC, int endR, int endC, Board board) {
        boolean isHorizontal = (startR == endR);
        boolean isVertical = (startC == endC);

        if (!isHorizontal && !isVertical) return false;
        if (startR == endR && startC == endC) return false;

        int rowStep = Integer.compare(endR, startR);
        int colStep = Integer.compare(endC, startC);
        int currentRow = startR + rowStep;
        int currentCol = startC + colStep;

        while (currentRow != endR || currentCol != endC) {
            if (board.getPiece(currentRow, currentCol) != null) return false;
            currentRow += rowStep;
            currentCol += colStep;
        }
        return true;
    }
}