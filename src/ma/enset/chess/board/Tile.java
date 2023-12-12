package ma.enset.chess.board;

import ma.enset.chess.pieces.Piece;

import java.util.Map;

public abstract class Tile {
    protected final int coordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILE = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        return null;
    }

    public Tile(final int coordinate) {
        this.coordinate = coordinate;
    }

    public abstract boolean isOccupied();

    public abstract Piece getPiece();
}
