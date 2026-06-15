import java.util.InputMismatchException;
import java.util.Scanner;
public class UI {
    public static void printBoard(
        ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == null) {
                    System.out.print("- ");
                }
                else {
                    System.out.print(
                        pieces[i][j] + " "
                    );
                }
            }
            System.out.println();
        }
        System.out.println(
            "  a b c d e f g h"
        );
        }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i = 0; i < pieces.length; i++) {
                System.out.print((8 - i) + " ");
                for (int j = 0; j < pieces[i].length; j++) {
                    if (possibleMoves[i][j]) {
                        System.out.print("* ");
                    }
                    else {
                        System.out.print("- ");
                    }
                }
                System.out.println();
            }
            System.out.println("  a b c d e f g h");
        }
    private static void printPiece(ChessPiece piece) {
        if (piece == null) {
            System.out.print("- ");
        } else {
            System.out.print(piece + " ");
        }
    }
    public static ChessPosition readChessPosition(
        Scanner sc) {
        try {
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        } catch (RuntimeException e) {
            throw new InputMismatchException(
                "Erro ao ler a posição. " +
                "Valores válidos são de a1 a h8."
             );
        }
    }
}