package ma.enset.chess.board;

import ma.enset.chess.pieces.Piece;

public abstract class Tile {
    protected final int coordinate;

    public Tile(int coordinate) {
        this.coordinate = coordinate;
    }

    public abstract boolean isOccupied();

    public abstract Piece getPiece();
}
