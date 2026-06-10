public class xadrezjogo {
    public static void main(String[] args) {
        Board board = new Board(8, 8);
        King king = new King(board, Color.WHITE);

        Position kingPos = new Position(4, 4);
        board.placePiece(king, kingPos);
        boolean[][] kingMoves = king.possibleMoves();

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                System.out.print(
                    kingMoves[i][j] ? "X " : "- "
                );
            }
            System.out.println();
        }
    }
}