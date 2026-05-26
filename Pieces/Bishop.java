package Pieces;

import Utils.Color;
import Board.Board;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color, color == Color.WHITE ? '♗' : '♝');
    }

    @Override
    public boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol, Board board) {

        if (srcRow == destRow && srcCol == destCol) return false;

        int rowDiff = Math.abs(destRow - srcRow);
        int colDiff = Math.abs(destCol - srcCol);
        if (rowDiff != colDiff) return false;

        int rowStep = (destRow > srcRow) ? 1 : -1;
        int colStep = (destCol > srcCol) ? 1 : -1;

        int currentRow = srcRow + rowStep;
        int currentCol = srcCol + colStep;

     while (currentRow != destRow && currentCol != destCol) {
            if (board.getPiece(currentRow, currentCol) != null) {
                return false; 
            }
            currentRow += rowStep;
            currentCol += colStep;
        }

        return true;
    }
}