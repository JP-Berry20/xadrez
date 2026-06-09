public class pecateste extends Piece {
    public pecateste(Board board) {
        super(board);
    }
    @Override
    public boolean[][] possibleMoves() {
        return new boolean[getBoard().getRows()][getBoard().getColumns()];
    }
}