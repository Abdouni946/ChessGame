package ma.enset.chess.model.ai;

import ma.enset.chess.model.game.ChessGameInterface;
import ma.enset.chess.model.util.Pair;
import ma.enset.chess.model.util.Tile;

public interface AIInterface {
    /**
     * Returns the bot's best move as a pair of tiles (key is starting tile, value is ending tile).
     */
    Pair<Tile, Tile> move();

    /**
     * Sets the current game for the bot.
     */
    void setGame(ChessGameInterface game);
}
