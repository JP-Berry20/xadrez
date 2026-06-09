public class xadrezjogo {
    public static void main(String[] args) {
        Board board = new Board(8, 8);

        System.out.println("Tabuleiro criado!");
        System.out.println("Linhas: " + board.getRows());
        System.out.println("Colunas: " + board.getColumns());

        Position pos = new Position(2, 3);

        pecateste piece = new pecateste(board);
        board.placePiece(piece, pos);
        System.out.println(board.thereIsAPiece(pos));

         board.removePiece(pos);
        System.out.println(board.thereIsAPiece(pos));
    }
}