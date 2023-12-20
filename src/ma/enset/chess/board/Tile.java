package ma.enset.chess.board;

import com.google.common.collect.ImmutableMap;
import ma.enset.chess.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {
    protected final int coordinate;
    private static final Map<Integer, EmptyTile> EMPTY_TILE_CACHE = createAllEmptyTiles();

    private static Map<Integer, EmptyTile> createAllEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < BoardUtils.NUM_TILES; ++i) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int coordinate, final Piece piece) {
        if (piece == null) {
            return EMPTY_TILE_CACHE.get(coordinate);
        }
        return new OccupiedTile(coordinate, piece);
    }

    Tile(final int coordinate) {
        this.coordinate = coordinate;
    }

    public abstract boolean isOccupied();

    public abstract Piece getPiece();
}
