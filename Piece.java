public abstract class Piece {
    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
    }
    protected Board getBoard() {
        return board;
    }
    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove() {
        boolean[][] moves = possibleMoves();
        for (int i = 0; i < getBoard().getRows(); i++) {
            for (int j = 0; j < getBoard().getColumns(); j++) {
                if (moves[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}