package ma.enset.chess.board;

public class EmptyTile extends Tile{
    public EmptyTile(int coordinate) {
        super(coordinate);
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
