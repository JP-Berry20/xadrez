public class Pawn extends ChessPiece {
    public Pawn(Board board, Color color) {
        super(board, color);
    }
    @Override
    public String toString() {
        return "P";
    }
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat =
            new boolean[getBoard().getRows()]
                       [getBoard().getColumns()];
        Position p = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            // anda 1 casa para frente
            p.setValues(
                position.getRow() - 1,
                position.getColumn()
            );
            if (getBoard().positionExists(p)
                    && !getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            // captura diagonal esquerda
            p.setValues(
                position.getRow() - 1,
                position.getColumn() - 1
            );
            if (getBoard().positionExists(p)
                    && isThereOpponentPiece(p)) {

                mat[p.getRow()][p.getColumn()] = true;
            }
            // captura diagonal direita
            p.setValues(
                position.getRow() - 1,
                position.getColumn() + 1
            );
            if (getBoard().positionExists(p)
                    && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
        } else {
            // anda 1 casa para frente
            p.setValues(
                position.getRow() + 1,
                position.getColumn()
            );
            if (getBoard().positionExists(p)
                    && !getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            // captura diagonal esquerda
            p.setValues(
                position.getRow() + 1,
                position.getColumn() - 1
            );
            if (getBoard().positionExists(p)
                    && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            // captura diagonal direita
            p.setValues(
                position.getRow() + 1,
                position.getColumn() + 1
            );
            if (getBoard().positionExists(p)
                    && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
        }
        return mat;
    }
}