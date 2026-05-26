package Pieces;

import Utils.Color;
import Board.Board;

public abstract class Piece {
    protected Color color;
    protected char symbol;

    public Piece(Color color, char symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    public Color getColor() { return color; }
    public char getSymbol() { return symbol; }

    @Override
    public String toString() {
        return color + " " + this.getClass().getSimpleName() + " (" + symbol + ")";
    }

    public abstract boolean isValidMove(int srcRow, int srcCol, int destRow, int destCol, Board board);
}