package ma.enset.chess.model.game;

import ma.enset.chess.model.util.Colors;
import ma.enset.chess.model.util.Pair;
import ma.enset.chess.model.util.Pieces;
import ma.enset.chess.model.util.Tile;

import java.util.List;
import java.util.Set;

public interface ChessGameInterface {
    boolean doesTileContainPieceOfCurrentPlayersColor(int row, int col);

    Set<Tile> getAvailableMovesForTile(int row, int col);

    void move(int startRow, int startCol, int endRow, int endCol);

    void undoLastMove();

    boolean isCurrentPlayerInCheck();

    boolean isCurrentPlayerInCheckmate();

    boolean isGameInStalemate();

    Pieces[][] getBoardState();

    List<Pieces> getActiveWhitePieces();

    List<Pieces> getActiveBlackPieces();

    List<Pieces> getCapturedWhitePieces();

    List<Pieces> getCapturedBlackPieces();

    Colors getCurrentPlayerColor();

    Set<Pair<Tile, Tile>> getAvailableMovesForCurrentPlayer();

    int getMaterialHeuristic();

    int getPositionalHeuristic();

    String getAlgebraicNotationForMove(Tile start, Tile end);
}
