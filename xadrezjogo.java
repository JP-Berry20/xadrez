public class xadrezjogo {
    public static void main(String[] args) {
        
        ChessMatch match = new ChessMatch();
        UI.printBoard(
            match.getPieces()
        );
    }
}