public class Queen extends ChessPiece {
    public Queen(Board board, Color color) {
        super(board, color);
    }
    @Override
    public String toString() {
        return "Q";
    }
    @Override
    public boolean[][] possibleMoves() {
    boolean[][] mat =
        new boolean[getBoard().getRows()]
                   [getBoard().getColumns()];
    Position p = new Position(0, 0);

    p.setValues( //cima
        position.getRow() - 1,
        position.getColumn()
    );
    while (getBoard().positionExists(p)
            && !getBoard().thereIsAPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;

        p.setValues(
            p.getRow() - 1,
            p.getColumn()
        );
    }
    if (getBoard().positionExists(p)
            && isThereOpponentPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
    }
    p.setValues( //esquerda
        position.getRow(),
        position.getColumn() - 1
    );
    while (getBoard().positionExists(p)
            && !getBoard().thereIsAPiece(p)) {

        mat[p.getRow()][p.getColumn()] = true;
        p.setValues(
            p.getRow(),
            p.getColumn() - 1
        );
    }
    if (getBoard().positionExists(p)
            && isThereOpponentPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
    }
    p.setValues( //direita
        position.getRow(),
        position.getColumn() + 1
    );
    while (getBoard().positionExists(p)
            && !getBoard().thereIsAPiece(p)) {

        mat[p.getRow()][p.getColumn()] = true;
        p.setValues(
            p.getRow(),
            p.getColumn() + 1
        );
    }
    if (getBoard().positionExists(p)
            && isThereOpponentPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
    }
    p.setValues( //baixo
        position.getRow() + 1,
        position.getColumn()
    );
    while (getBoard().positionExists(p)
            && !getBoard().thereIsAPiece(p)) {

        mat[p.getRow()][p.getColumn()] = true;
        p.setValues(
            p.getRow() + 1,
            p.getColumn()
        );
    }
    if (getBoard().positionExists(p)
            && isThereOpponentPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
            }   
    p.setValues( // noroeste
        position.getRow() - 1,
        position.getColumn() - 1
    );
    while (getBoard().positionExists(p)
        && !getBoard().thereIsAPiece(p)) {
    mat[p.getRow()][p.getColumn()] = true;
    p.setValues(
        p.getRow() - 1,
        p.getColumn() - 1
    );
    }
    if (getBoard().positionExists(p)
        && isThereOpponentPiece(p)) {
    mat[p.getRow()][p.getColumn()] = true;
    }
    p.setValues( // nordeste
        position.getRow() - 1,
        position.getColumn() + 1
    );
    while (getBoard().positionExists(p)
        && !getBoard().thereIsAPiece(p)) {
    mat[p.getRow()][p.getColumn()] = true;
    p.setValues(
        p.getRow() - 1,
        p.getColumn() + 1
    );
    }
    if (getBoard().positionExists(p)
        && isThereOpponentPiece(p)) {
    mat[p.getRow()][p.getColumn()] = true;
    }
    p.setValues( // sudoeste
        position.getRow() + 1,
        position.getColumn() - 1
    );
    while (getBoard().positionExists(p)
        && !getBoard().thereIsAPiece(p)) {
    mat[p.getRow()][p.getColumn()] = true;
    p.setValues(
        p.getRow() + 1,
        p.getColumn() - 1
    );
    }
    if (getBoard().positionExists(p)
        && isThereOpponentPiece(p)) {
    mat[p.getRow()][p.getColumn()] = true;
    }
    p.setValues( // sudeste
        position.getRow() + 1,
        position.getColumn() + 1
    );
    while (getBoard().positionExists(p)
        && !getBoard().thereIsAPiece(p)) {
    mat[p.getRow()][p.getColumn()] = true;
    p.setValues(
        p.getRow() + 1,
        p.getColumn() + 1
    );
    }
    if (getBoard().positionExists(p)
        && isThereOpponentPiece(p)) {
    mat[p.getRow()][p.getColumn()] = true;
    }
    return mat;
    }
}