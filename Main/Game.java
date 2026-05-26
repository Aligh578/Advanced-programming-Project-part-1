package Main;

import java.util.Scanner;
import Utils.Color;
import Board.Board;
import Pieces.Piece;

public class Game {
    private Board board;
    private Color currentTurn;

    public Game() {
        this.board = new Board();
        this.currentTurn = Color.WHITE;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== به بازی شطرنج خوش آمدید (فاز ۱) ===");
        System.out.println("---------------------------------------");
        System.out.println("نکته: مختصات مبدا و مقصد را با فاصله وارد کنید.");

        while (true) {
            board.printBoard();
            System.out.print("نوبت [" + (currentTurn == Color.WHITE ? "سفید" : "سیاه") + "] -> حرکت (مثال e2 e4): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) break;

            if (processMove(input)) {
                currentTurn = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
            } else {
                System.out.println("❌ حرکت نامعتبر!");
            }
        }
        scanner.close();
    }

    private boolean processMove(String input) {
        input = input.replace("-", "").replace(" ", "").toLowerCase();
        if (input.length() != 4) return false;

        int startC = input.charAt(0) - 'a';
        int endC = input.charAt(2) - 'a';
        int startR = 8 - Character.getNumericValue(input.charAt(1));
        int endR = 8 - Character.getNumericValue(input.charAt(3));

        if (!board.isWithinBounds(startR, startC) || !board.isWithinBounds(endR, endC)) return false;

        Piece movingPiece = board.getPiece(startR, startC);
        Piece targetPiece = board.getPiece(endR, endC);

        if (movingPiece == null || movingPiece.getColor() != currentTurn) return false;
        if (targetPiece != null && targetPiece.getColor() == currentTurn) return false;

        if (!movingPiece.isValidMove(startR, startC, endR, endC, board)) return false;

        board.setPiece(endR, endC, movingPiece);
        board.setPiece(startR, startC, null);
        return true;
    }
}