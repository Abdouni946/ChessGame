package ma.enset.chess;

import ma.enset.chess.engine.board.Board;
import ma.enset.chess.gui.Table;

public class Chess {
    public static void main(String[] args) {
        Board board = Board.CreateStandardBoard();

        System.out.println(board);

        Table table = new Table();
    }
}
