package Pieces;

import Utils.Color;
import Board.Board;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color, color == Color.WHITE ? 'Q' : 'q');
    }

    @Override
    public boolean isValidMove(int startR, int startC, int endR, int endC, Board board) {
        System.out.println("[Log] Evaluating Queen path clearance...");
        int rowDiff = Math.abs(startR - endR);
        int colDiff = Math.abs(startC - endC);

        if (rowDiff != 0 && colDiff != 0 && rowDiff != colDiff) return false;

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