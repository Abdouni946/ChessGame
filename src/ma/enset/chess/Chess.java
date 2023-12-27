package ma.enset.chess;

import ma.enset.chess.engine.board.Board;

public class Chess {
    public static void main(String[] args) {
        Board board = Board.CreateStandardBoard();

        System.out.println(board);
    }
}
