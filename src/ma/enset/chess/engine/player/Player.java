package ma.enset.chess.engine.player;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import ma.enset.chess.engine.Alliance;
import ma.enset.chess.engine.board.Board;
import ma.enset.chess.engine.board.Move;
import ma.enset.chess.engine.pieces.King;
import ma.enset.chess.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {
    protected final Board board;
    protected King playerKing; // most important piece
    protected final Collection<Move> legalMoves;
    private final boolean isInCheck;

    public Player(Board board, Collection<Move> legalMoves, Collection<Move> opponentMove) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = ImmutableList.copyOf(Iterables.concat(legalMoves, calculateKingCastles(legalMoves, opponentMove)));
        this.isInCheck = !Player.calculateAttackMove(this.playerKing.getPosition(), opponentMove).isEmpty();
    }

    public abstract Collection<Piece> getActivePieces();

    protected abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentsLegals);

    public abstract Alliance getAlliance();

    public abstract Player getOpponent();

    //Methodes
    private Piece getPlayerKing() {
        return this.playerKing;
    }

    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }

    public boolean isLegalMove(final Move move) {
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck() {
        return this.isInCheck;
    }

    public boolean isInCheckmate() {
        return this.isInCheck && !hasEscapeMoves();
    }

    public boolean isInStaleMate() {
        return !this.isInCheck && !hasEscapeMoves();
    }

    protected boolean hasEscapeMoves() {
        for (final Move move : this.legalMoves) {
            final MoveTransition transition = makeMove(move);
            if (transition.getMoveStatus().isDone())
                return true;
        }
        return false;
    }

    public boolean isCastled() {
        return false;
    }

    public MoveTransition makeMove(final Move move) {
        if (!isLegalMove(move)) {
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE);
        }
        final Board transitionBoard = move.execute();
        final Collection<Move> kingAttacks = Player.calculateAttackMove(transitionBoard.getCurrentPlayer().getOpponent().getPlayerKing().getPosition(), transitionBoard.getCurrentPlayer().getLegalMoves());
        if (!kingAttacks.isEmpty()) {
            return new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK);
        }
        return new MoveTransition(this.board, move, MoveStatus.DONE);
    }

    protected static Collection<Move> calculateAttackMove(int position, Collection<Move> moves) {
        final List<Move> attackMoves = new ArrayList<>();
        for (final Move move : moves) {
            if (position == move.getDestinationCoordinate()) {
                attackMoves.add(move);
            }
        }
        return ImmutableList.copyOf(attackMoves);
    }

    private King establishKing() {
        for (final Piece piece : getActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("Not a valid board");
    }

}
