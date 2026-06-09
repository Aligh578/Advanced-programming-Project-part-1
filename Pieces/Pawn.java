package Pieces;

import Utils.Color;
import Board.Board;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, color == Color.WHITE ? '\u2659' : '\u265F');
    }

    @Override
    public boolean isValidMove(int startR, int startC, int endR, int endC, Board board) {
        int direction = (this.getColor() == Utils.Color.WHITE) ? -1 : 1;
        int startingRow = (this.getColor() == Utils.Color.WHITE) ? 6 : 1;

        if (startC == endC && endR == startR + direction) {
            return board.getPiece(endR, endC) == null;
        }
        
        if (startC == endC && startR == startingRow && endR == startR + (2 * direction)) {
            return board.getPiece(startR + direction, startC) == null && board.getPiece(endR, endC) == null;
        }
        

        if (Math.abs(startC - endC) == 1 && endR == startR + direction) {
            

            if (board.getPiece(endR, endC) != null) {
                return true;
            }
            

            if (board.getPiece(endR, endC) == null) {
                Piece adjacentPiece = board.getPiece(startR, endC);
                
                if (adjacentPiece instanceof Pawn && adjacentPiece.getColor() != this.getColor()) {
                    int expectedLastStartRow = startR + (direction * 2);
                    

                    if (board.getLastMoveStartRow() == expectedLastStartRow && 
                        board.getLastMoveStartCol() == endC &&
                        board.getLastMoveEndRow() == startR &&
                        board.getLastMoveEndCol() == endC) {
                        return true;
                    }
                }
            }
        }
    
        return false; 
    }
}