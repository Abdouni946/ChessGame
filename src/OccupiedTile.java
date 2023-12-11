public class OccupiedTile extends Tile{
    Piece piece;

    public OccupiedTile(int coordinate) {
        super(coordinate);
    }

    @Override
    public boolean isOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return piece;
    }
}
