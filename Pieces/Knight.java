package Pieces;

import Utils.Color;
import Board.Board;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color, color == Color.WHITE ? '♘' : '♞');
    }

    @Override
    public boolean isValidMove(int startR, int startC, int endR, int endC, Board board) {
        int rowDiff = Math.abs(startR - endR);
        int colDiff = Math.abs(startC - endC);
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}