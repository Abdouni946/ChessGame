package ma.enset.chess.board;

import ma.enset.chess.pieces.Piece;

public abstract class Move {
    private final Board board;
    private final Piece movedPiece;
    private final int destinationCoordinate;

    public Move(Board board, Piece movedPiece, int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }
}
