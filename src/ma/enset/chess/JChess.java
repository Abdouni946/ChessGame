package ma.enset.chess;

import ma.enset.chess.board.Board;

public class JChess {
    public static void main(String[] args) {

        Board board = Board.CreateStandardBoard();

        System.out.println(board);
    }
}
