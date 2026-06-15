import java.util.ArrayList;
import java.util.List;

public class ChessMatch {
    private Board board;
    private int turn;
    private Color currentPlayer;
    private List<Piece> capturedPieces =
        new ArrayList<>();

    public Board getBoard() {
        return board;
    }

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }
    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat =
            new ChessPiece[board.getRows()]
                          [board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] =
                    (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }
    public ChessPiece performChessMove(
        ChessPosition sourcePosition,
        ChessPosition targetPosition) {

        Position source =
            sourcePosition.toPosition();
        Position target =
            targetPosition.toPosition();
        validateSourcePosition(source);

        validateTargetPosition(source, target);
        Piece capturedPiece =
            makeMove(source, target);
        nextTurn();
        return (ChessPiece) capturedPiece;
    }
    private void placeNewPiece(
        char column,
        int row,
        ChessPiece piece) {

        board.placePiece(
            piece,
            new ChessPosition(column, row)
                .toPosition()
        );
    }
    private Piece makeMove(
        Position source,
        Position target) {

        ChessPiece p =
            (ChessPiece) board.removePiece(source);
        p.increaseMoveCount();
        Piece capturedPiece =
            board.removePiece(target);
        board.placePiece(p, target);

        if (capturedPiece != null) {
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }
    private void nextTurn() {
        turn++;
        currentPlayer =
            (currentPlayer == Color.WHITE)
            ? Color.BLACK
            : Color.WHITE;
    }
    public int getTurn() {
        return turn;
    }
    public Color getCurrentPlayer() {
        return currentPlayer;
    }
    private void validateSourcePosition(
        Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException(
                "Não existe peça na posição de origem."
            );
        }
        if (((ChessPiece) board.piece(position))
                .getColor() != currentPlayer) {
            throw new ChessException(
                "A peça escolhida não é sua."
            );
        }
        if (!board.piece(position)
                .isThereAnyPossibleMove()) {
            throw new ChessException(
                "Não há movimentos possíveis para essa peça."
            );
        }
    }
    private void validateTargetPosition(
        Position source,
        Position target) {

        if (!board.piece(source)
                .possibleMove(target)) {
            throw new ChessException(
                "A peça escolhida não pode se mover para a posição de destino."
            );
        }
    }
    public boolean[][] possibleMoves(
        ChessPosition sourcePosition) {
        Position position =
            sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position)
                    .possibleMoves();
    }
    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));

        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));

        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));

        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));

        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('e', 8, new King(board, Color.BLACK));

        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
    }
}