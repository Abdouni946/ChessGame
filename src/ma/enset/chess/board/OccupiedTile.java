package ma.enset.chess.board;

import ma.enset.chess.pieces.Piece;

public final class OccupiedTile extends Tile{
    private final Piece piece;

    OccupiedTile(final int coordinate, final Piece piece) {
        super(coordinate);
        this.piece = piece;
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
