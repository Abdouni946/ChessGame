package ma.enset.chess.board;

import com.google.common.collect.ImmutableMap;
import ma.enset.chess.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {
    protected final int coordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILE = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < 64; ++i) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int coordinate, final Piece piece) {
        if (piece == null) {
            return EMPTY_TILE.get(coordinate);
        }
        return new OccupiedTile(coordinate, piece);
    }

    private Tile(final int coordinate) {
        this.coordinate = coordinate;
    }

    public abstract boolean isOccupied();

    public abstract Piece getPiece();
}
