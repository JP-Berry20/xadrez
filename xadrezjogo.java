public class xadrezjogo {
    public static void main(String[] args) {
        Board board = new Board(8, 8);

        System.out.println("Tabuleiro criado!");
        System.out.println("Linhas: " + board.getRows());
        System.out.println("Colunas: " + board.getColumns());
    }
}