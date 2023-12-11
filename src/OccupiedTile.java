public class OccupiedTile extends Tile{
    Piece piece;

    public OccupiedTile(int coordinate, Piece piece) {
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
