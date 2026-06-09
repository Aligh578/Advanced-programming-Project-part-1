package Pieces;

import Utils.Color;
import Board.Board;

public class King extends Piece {
    public King(Color color) {
        super(color, color == Color.WHITE ? '\u2654' : '\u265A');
    }

    @Override
    public boolean isValidMove(int startR, int startC, int endR, int endC, Board board) {
        int rowDiff = Math.abs(startR - endR);
        int colDiff = Math.abs(startC - endC);

        if (rowDiff <= 1 && colDiff <= 1) {
            return true; 
        }

        if (rowDiff == 0 && colDiff == 2 && !this.hasMoved()) {

            Utils.Color attackerColor = (this.getColor() == Utils.Color.WHITE) ? Utils.Color.BLACK : Utils.Color.WHITE;

            if (board.isInCheck(this.getColor())) {
                return false;
            }
            
            int rookSourceCol = (endC > startC) ? 7 : 0; 
            Piece rook = board.getPiece(startR, rookSourceCol);

            if (rook instanceof Pieces.Rook && !rook.hasMoved()) {
                int step = (endC > startC) ? 1 : -1;

                for (int i = 1; i <= 2; i++) {
                    int checkCol = startC + (i * step);
                    if (board.isSquareAttacked(startR, checkCol, attackerColor)) {
                        return false; 
                    }
                }

                int currCol = startC + step;
                while (currCol != rookSourceCol) {
                    if (board.getPiece(startR, currCol) != null) {
                        return false; 
                    }
                    currCol += step;
                }

                return true; 
            }
        }
        return false;
    }
}