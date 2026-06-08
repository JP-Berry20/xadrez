public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new BoardException(
                "Erro criando tabuleiro: é necessário pelo menos 1 linha e 1 coluna."
            );
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
}
