import java.util.ArrayList;
import java.util.List;

public class ChessMatch {
    private Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;
    private List<Piece> capturedPieces =
        new ArrayList<>();
    private List<Piece> piecesOnTheBoard =
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
        ChessPiece movedPiece =
            (ChessPiece) board.piece(target);

            promoted = null;
            if (movedPiece instanceof Pawn) {
                if ((movedPiece.getColor() == Color.WHITE
                    && target.getRow() == 0)
                    || (movedPiece.getColor() == Color.BLACK
                    && target.getRow() == 7)) {
                    promoted = movedPiece;
                }
            }
        if (testCheck(currentPlayer)) {
            undoMove(
                source,
                target,
                capturedPiece
            );
            throw new ChessException(
                "Você não pode se colocar em xeque."
            );
        }
        if (movedPiece instanceof Pawn
            && Math.abs(
                target.getRow()
                - source.getRow()
            ) == 2) {
            enPassantVulnerable =
                movedPiece;
        }
        else {
            enPassantVulnerable =
                null;
        }
        check = testCheck(
            opponent(currentPlayer)
        );
        if (testCheckMate(
                opponent(currentPlayer))) {
            checkMate = true;
        }
        else {
            nextTurn();
        }
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
        piecesOnTheBoard.add(piece);
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

        if (p instanceof Pawn) {
            if (source.getColumn()
                    != target.getColumn()
                    && capturedPiece == null) {
                Position pawnPosition;
                if (p.getColor()
                        == Color.WHITE) {

                    pawnPosition =
                        new Position(
                            target.getRow() + 1,
                            target.getColumn()
                        );
                }
                else {
                    pawnPosition =
                        new Position(
                            target.getRow() - 1,
                            target.getColumn()
                        );
                }
                capturedPiece =
                    board.removePiece(
                        pawnPosition
                    );
                capturedPieces.add(
                    capturedPiece
                );

                piecesOnTheBoard.remove(
                    capturedPiece
                );
            }
        }
        if (p instanceof King
                && target.getColumn()
                == source.getColumn() + 2) {
            Position sourceT =
                new Position(
                    source.getRow(),
                    source.getColumn() + 3
                );
            Position targetT =
                new Position(
                    source.getRow(),
                    source.getColumn() + 1
                );
            ChessPiece rook =
                (ChessPiece)
                board.removePiece(sourceT);
            board.placePiece(
                rook,
                targetT
            );
            rook.increaseMoveCount();
        }
        if (p instanceof King
            && target.getColumn()
                == source.getColumn() - 2) {
            Position sourceT =
                new Position(
                    source.getRow(),
                    source.getColumn() - 4
                );
            Position targetT =
                new Position(
                    source.getRow(),
                    source.getColumn() - 1
                );
            ChessPiece rook =
                (ChessPiece)
                board.removePiece(sourceT);
            board.placePiece(
                rook,
                targetT
            );
            rook.increaseMoveCount();
        }

        if (capturedPiece != null) {
            capturedPieces.add(capturedPiece);
            piecesOnTheBoard.remove(
                capturedPiece
            );
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
    public boolean getCheck() {
        return check;
    }
    private ChessPiece king(Color color) {
        for (Piece p : piecesOnTheBoard) {
            ChessPiece cp = (ChessPiece) p;
            if (cp.getColor() == color
                    && cp instanceof King) {
                return cp;
            }
        }
        throw new IllegalStateException(
            "Não existe rei da cor "
            + color
            + " no tabuleiro."
        );
    }
    private boolean testCheck(Color color) {
        Position kingPosition =
            king(color)
                .getChessPosition();
        for (Piece p : piecesOnTheBoard) {
            ChessPiece cp =
                (ChessPiece) p;
            if (cp.getColor() != color) {
                boolean[][] mat =
                    cp.possibleMoves();
                if (mat[
                    kingPosition.getRow()
                ][
                    kingPosition.getColumn()
                ]) {
                    return true;
                }
            }
        }
        return false;
    }
    private Color opponent(Color color) {
        return (color == Color.WHITE)
            ? Color.BLACK
            : Color.WHITE;
    }
    private void undoMove(
        Position source,
        Position target,
        Piece capturedPiece) {
        ChessPiece p =
            (ChessPiece) board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p, source);

        if (p instanceof Pawn) {
            if (source.getColumn()
                    != target.getColumn()
                    && capturedPiece
                        == enPassantVulnerable) {
                ChessPiece pawn =
                    (ChessPiece)
                    board.removePiece(target);
                Position pawnPosition;
                if (p.getColor()
                        == Color.WHITE) {
                    pawnPosition =
                        new Position(
                            3,
                            target.getColumn()
                        );
                }
                else {
                    pawnPosition =
                        new Position(
                            4,
                            target.getColumn()
                        );
                }
                board.placePiece(
                    pawn,
                    pawnPosition
                );
            }
        }
        if (p instanceof King
                && target.getColumn()
                == source.getColumn() + 2) {

            Position sourceT =
                new Position(
                    source.getRow(),
                    source.getColumn() + 3
                );
            Position targetT =
                new Position(
                    source.getRow(),
                    source.getColumn() + 1
                );
            ChessPiece rook =
                (ChessPiece)
                board.removePiece(targetT);
            board.placePiece(
                rook,
                sourceT
            );
            rook.decreaseMoveCount();
        }
        if (p instanceof King
            && target.getColumn()
                == source.getColumn() - 2) {
            Position sourceT =
                new Position(
                    source.getRow(),
                    source.getColumn() - 4
                );
            Position targetT =
                new Position(
                    source.getRow(),
                    source.getColumn() - 1
                );
            ChessPiece rook =
                (ChessPiece)
                board.removePiece(targetT);
            board.placePiece(
                rook,
                sourceT
            );
            rook.decreaseMoveCount();
        }

        if (capturedPiece != null
            && !(p instanceof Pawn
                && source.getColumn()
                    != target.getColumn()
                && capturedPiece
                    == enPassantVulnerable)) {
            board.placePiece(
                capturedPiece,
                target
            );
            capturedPieces.remove(
                capturedPiece
            );
            piecesOnTheBoard.add(
                capturedPiece
            );
        }
    }
    public boolean getCheckMate() {
        return checkMate;
    }
    private boolean testCheckMate(Color color) {
        if (!testCheck(color)) {
            return false;
        }
        List<Piece> list =
            new ArrayList<>(piecesOnTheBoard);
        for (Piece p : list) {
            ChessPiece cp = (ChessPiece) p;
            if (cp.getColor() == color) {
                boolean[][] mat =
                    cp.possibleMoves();
                for (int i = 0; i < board.getRows(); i++) {
                    for (int j = 0; j < board.getColumns(); j++) {
                        if (mat[i][j]) {
                            Position source =
                                cp.getChessPosition();

                            Position target =
                                new Position(i, j);
                            Piece capturedPiece =
                                makeMove(source, target);
                            boolean testCheck =
                                testCheck(color);
                            undoMove(
                                source,
                                target,
                                capturedPiece
                            );
                            if (!testCheck) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    public ChessPiece getEnPassantVulnerable() {
        return enPassantVulnerable;
    }
    public ChessPiece getPromoted() {
        return promoted;
    }
    public ChessPiece replacePromotedPiece(
        String type) {
        Position pos =
            promoted.getChessPosition();
        Piece p =
            board.removePiece(pos);
        piecesOnTheBoard.remove(p);
        ChessPiece newPiece;
        //a
        if (type.equals("D")) {
            newPiece =
                new Queen(
                    board,
                    promoted.getColor()
                );
        }
        else if (type.equals("T")) {
            newPiece =
                new Rook(
                    board,
                    promoted.getColor()
                );
        }
        else if (type.equals("B")) {
            newPiece =
                new Bishop(
                    board,
                    promoted.getColor()
                );
        }
        else {
            newPiece =
                new Knight(
                    board,
                    promoted.getColor()
                );
        }
        board.placePiece(
            newPiece,
            pos
        );
        piecesOnTheBoard.add(
            newPiece
        );
        return newPiece;
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

        placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
    }
}