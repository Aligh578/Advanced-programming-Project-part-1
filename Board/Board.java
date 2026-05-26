package Board;

import Utils.Color;
import Pieces.*; // این یعنی همه مهره‌های داخل پوشه Pieces را ایمپورت کن

public class Board {
    private Piece[][] grid;

    public Board() {
        grid = new Piece[8][8];
        initializeBoard();
    }
    private void initializeBoard() {
        // چیدن سیاه
        grid[0][0] = new Rook(Color.BLACK);
        grid[0][1] = new Knight(Color.BLACK);
        grid[0][2] = new Bishop(Color.BLACK);
        grid[0][3] = new Queen(Color.BLACK);
        grid[0][4] = new King(Color.BLACK);
        grid[0][5] = new Bishop(Color.BLACK);
        grid[0][6] = new Knight(Color.BLACK);
        grid[0][7] = new Rook(Color.BLACK);
        for (int i = 0; i < 8; i++) grid[1][i] = new Pawn(Color.BLACK);

        // چیدن سفید
        for (int i = 0; i < 8; i++) grid[6][i] = new Pawn(Color.WHITE);
        grid[7][0] = new Rook(Color.WHITE);
        grid[7][1] = new Knight(Color.WHITE);
        grid[7][2] = new Bishop(Color.WHITE);
        grid[7][3] = new Queen(Color.WHITE);
        grid[7][4] = new King(Color.WHITE);
        grid[7][5] = new Bishop(Color.WHITE);
        grid[7][6] = new Knight(Color.WHITE);
        grid[7][7] = new Rook(Color.WHITE);
    }

    public Piece getPiece(int row, int col) {
        if (isWithinBounds(row, col)) return grid[row][col];
        return null;
    }

    public void setPiece(int row, int col, Piece piece) {
        if (isWithinBounds(row, col)) grid[row][col] = piece;
    }

    public boolean isWithinBounds(int row, int col) {
        boolean rowCheck = (row >= 0 && row < 8);
        boolean colCheck = (col >= 0 && col < 8);
        return rowCheck && colCheck;
    }

    public void printBoard() {
        System.out.println();
        for (int row = 0; row < 8; row++) {
            System.out.print((8 - row) + " "); 
            for (int col = 0; col < 8; col++) {
                if (grid[row][col] == null) {
                    System.out.print(" . ");
                } else {
                    System.out.print(grid[row][col].getSymbol() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h\n");
    }
}