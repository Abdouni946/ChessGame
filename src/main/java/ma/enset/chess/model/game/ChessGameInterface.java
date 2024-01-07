package ma.enset.chess.model.game;

import ma.enset.chess.model.util.Colors;
import ma.enset.chess.model.util.Pair;
import ma.enset.chess.model.util.Pieces;
import ma.enset.chess.model.util.Tile;

import java.util.List;
import java.util.Set;

/*
 *  Note that the following methods use 0-indexed rows and columns to refer to the spaces on the chess board.
 */
public interface ChessGameInterface {
    /**
     * @param row
     * @param col
     * @return true if the space at (row, col) contains a piece of the current player's color, false otherwise.
     * @throws IllegalArgumentException if either row or col are not between 0 and 7 (inclusive).
     */
    boolean doesTileContainPieceOfCurrentPlayersColor(int row, int col);

    /**
     * Gives a set of the spaces that the piece at the given space can move to.
     *
     * @param row
     * @param col
     * @return
     * @throws IllegalArgumentException if
     *                                  - the space doesn't contain a piece, or
     *                                  - the space contains a piece of the color of the inactive player, or
     *                                  - the game is over.
     */
    Set<Tile> getAvailableMovesForTile(int row, int col);

    /**
     * Moves the piece at the startRow row and startCol column to the space at the endRow row and endCol column.
     *
     * @param startRow
     * @param startCol
     * @param endRow
     * @param endCol
     * @throws IllegalArgumentException if:
     *                                  - the starting space doesn't contain the current player's piece, or
     *                                  - the ending space isn't available, or
     *                                  - the game is over
     */
    void move(int startRow, int startCol, int endRow, int endCol);

    /**
     * Reverts the game to it's state prior to the last move that was made.
     */
    void undoLastMove();

    /**
     * @return true if the current player is in check, false otherwise.
     */
    boolean isCurrentPlayerInCheck();

    /**
     * @return true if the current player is in checkmate, false otherwise.
     */
    boolean isCurrentPlayerInCheckmate();

    /**
     * @return true if the game is in stalemate, false otherwise.
     */
    boolean isGameInStalemate();

    /**
     * @return the state of the board as a 2D array of Pieces.
     */
    Pieces[][] getBoardState();

    /**
     * @return a list of the white pieces on the board, sorted in descending order.
     */
    List<Pieces> getActiveWhitePieces();

    /**
     * @return a list of the black pieces on the board, sorted in descending order.
     */
    List<Pieces> getActiveBlackPieces();

    /**
     * @return a list of the captured white pieces, sorted in descending order.
     */
    List<Pieces> getCapturedWhitePieces();

    /**
     * @return a list of the captured black pieces, sorted in descending order.
     */
    List<Pieces> getCapturedBlackPieces();

    /**
     * @return the current player's color.
     */
    Colors getCurrentPlayerColor();

    /**
     * @return the available moves for the current player as a set of pairs of tiles.
     */
    Set<Pair<Tile, Tile>> getAvailableMovesForCurrentPlayer();

    /**
     * @return the material heuristic to be used in the bots' heuristic function.
     */
    int getMaterialHeuristic();

    /**
     * @return the positional heuristic to be used in the bots' heuristic function.
     */
    int getPositionalHeuristic();

    /**
     * Gives the short algebraic notation for the given movement.
     *
     * @param start
     * @param end
     * @return
     */
    String getAlgebraicNotationForMove(Tile start, Tile end);
}
