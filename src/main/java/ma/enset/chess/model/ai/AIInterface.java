package ma.enset.chess.model.ai;

import ma.enset.chess.model.game.ChessGameInterface;
import ma.enset.chess.model.util.Pair;
import ma.enset.chess.model.util.Tile;

public interface AIInterface {
    Pair<Tile, Tile> move();

    void setGame(ChessGameInterface game);
}