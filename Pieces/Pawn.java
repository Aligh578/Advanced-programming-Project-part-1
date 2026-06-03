package Pieces;

import Utils.Color;
import Board.Board;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, color == Color.WHITE ? '\u2659' : '\u265F');
    }

    @Override
    public boolean isValidMove(int startR, int startC, int endR, int endC, Board board) {
        int direction = (this.color == Color.WHITE) ? -1 : 1;
        int startingRow = (this.color == Color.WHITE) ? 6 : 1;

        if (startC == endC && endR == startR + direction) {
            return board.getPiece(endR, endC) == null;
        }
        if (startC == endC && startR == startingRow && endR == startR + (2 * direction)) {
            return board.getPiece(startR + direction, startC) == null && board.getPiece(endR, endC) == null;
        }
        if (Math.abs(startC - endC) == 1 && endR == startR + direction) {
            return board.getPiece(endR, endC) != null;
        }
        return false;
    }
}