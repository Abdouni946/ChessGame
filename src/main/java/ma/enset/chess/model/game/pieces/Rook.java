package ma.enset.chess.model.game.pieces;

import ma.enset.chess.model.game.ChessGame;
import ma.enset.chess.model.game.MoveCommand;
import ma.enset.chess.model.util.Colors;
import ma.enset.chess.model.util.Tile;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {
    public Rook(ChessGame game, Tile tile, Colors color) {
        super(game, tile, color, 5);
    }

    @Override
    public MoveCommand createMoveCommand(Tile tile) {
        return createStandardMoveCommand(tile);
    }

    @Override
    public Set<Tile> getPotentiallyAvailableTiles() {
        Set<Tile> potentiallyAvailableTiles = new HashSet<>();
        addPotentiallyAvailableRectangularTiles(potentiallyAvailableTiles);
        return potentiallyAvailableTiles;
    }
}
