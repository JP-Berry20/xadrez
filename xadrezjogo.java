public class xadrezjogo {
    public static void main(String[] args) {

        ChessMatch match = new ChessMatch();
        UI.printBoard(match.getPieces());


        //teste da rainha
        Board board = new Board(8, 8);
        Queen queen = new Queen(board, Color.WHITE);
        Position queenPos = new Position(4, 4);
        board.placePiece(queen, queenPos);

        boolean[][] moves = queen.possibleMoves();
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                System.out.print(
                    moves[i][j] ? "X " : "- "
                );
            }
            System.out.println();
        }
        //teste da rainha
    }
}