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

    private Tile(final int coordinate) {
        this.coordinate = coordinate;
    }

    public abstract boolean isOccupied();

    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile{
        EmptyTile(final int coordinate) {
            super(coordinate);
        }

        @Override
        public String toString(){
            return "-";
        }

        @Override
        public boolean isOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile{
        private final Piece piece;

        OccupiedTile(final int coordinate, final Piece piece) {
            super(coordinate);
            this.piece = piece;
        }
        @Override
        public String toString(){
            return getPiece().getAlliance().isBlack() ? getPiece().toString().toLowerCase() :
                    getPiece().toString();
        }
        @Override
        public boolean isOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.piece;
        }
    }
}
