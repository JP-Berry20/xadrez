import java.util.InputMismatchException;
import java.util.Scanner;
public class xadrezjogo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ChessMatch chessMatch =
            new ChessMatch();
        while (!chessMatch.getCheckMate()) {
            try {
                System.out.println();
                    UI.printBoard(
                    chessMatch.getPieces()
                );
                System.out.println();

                System.out.println(
                    "Turno: " +
                    chessMatch.getTurn()
                );
                System.out.println(
                    "Jogador: " +
                    chessMatch.getCurrentPlayer()
                );
                if (chessMatch.getCheck()) {
                    System.out.println("XEQUE!");
                }
                System.out.println();
                System.out.print("Origem: ");

                ChessPosition source =
                    UI.readChessPosition(sc);
                    boolean[][] possibleMoves =
                        chessMatch.possibleMoves(source);
                    UI.printBoard(
                        chessMatch.getPieces(),
                        possibleMoves
                    );                
                System.out.print("Destino: ");

                ChessPosition target =
                    UI.readChessPosition(sc);
                chessMatch.performChessMove(
                    source,
                    target
                );
            }
            catch (ChessException e) {
                System.out.println(e.getMessage());
            }
            catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println();
        UI.printBoard(
            chessMatch.getPieces()
        );
        System.out.println();
        System.out.println("XEQUE-MATE!");
        System.out.println(
            "Vencedor: " +
            chessMatch.getCurrentPlayer()
        );
    }
}