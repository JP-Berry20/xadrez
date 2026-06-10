public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }
    @Override
    public String toString() {
        return "K";
    }
    private boolean canMove(Position position) {
        ChessPiece p =
            (ChessPiece)getBoard().piece(position);
        return p == null
            || p.getColor() != getColor();
    }
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat =
            new boolean[getBoard().getRows()]
                       [getBoard().getColumns()];
        Position p = new Position(0, 0);

        p.setValues( // acima
            position.getRow() - 1,
            position.getColumn()
        );
        if (getBoard().positionExists(p)
                && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        p.setValues( // baixo
            position.getRow() + 1,
            position.getColumn()
        );
        if (getBoard().positionExists(p)
                && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        p.setValues( // esquerda
            position.getRow(),
            position.getColumn() - 1
        );
        if (getBoard().positionExists(p)
                && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        p.setValues( // direita
            position.getRow(),
            position.getColumn() + 1
        );
        if (getBoard().positionExists(p)
                && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        p.setValues( // noroeste
        position.getRow() - 1,
        position.getColumn() - 1
        );
        if (getBoard().positionExists(p)
            && canMove(p)) {
        mat[p.getRow()][p.getColumn()] = true;
        }
        p.setValues( // nordeste
        position.getRow() - 1,
        position.getColumn() + 1
        );
        if (getBoard().positionExists(p)
            && canMove(p)) {
        mat[p.getRow()][p.getColumn()] = true;
        }
        p.setValues( // sudoeste
        position.getRow() + 1,
        position.getColumn() - 1
        );
        if (getBoard().positionExists(p)
            && canMove(p)) {
        mat[p.getRow()][p.getColumn()] = true;
        }
        p.setValues( // sudeste
        position.getRow() + 1,
        position.getColumn() + 1
        );
        if (getBoard().positionExists(p)
            && canMove(p)) {

        mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }
}