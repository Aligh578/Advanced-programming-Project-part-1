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

    public abstract boolean isValidMove(int startR, int startC, int endR, int endC, Board board);
}