package ma.enset.chess.player;

import com.google.common.collect.ImmutableList;
import ma.enset.chess.Alliance;
import ma.enset.chess.board.Board;
import ma.enset.chess.board.Move;
import ma.enset.chess.pieces.King;
import ma.enset.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class player {
    protected final Board board;
    protected King playerKing; // most important piece
    protected final Collection<Move> legalMoves;

    public abstract Collection<Piece> getActivePieces();

    public abstract Alliance getAlliance();

    public abstract player getOpponent();

    private final boolean isInCheck;

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
        final Collection<Move> kingAttacks = player.calculateAttackMove(transitionBoard.getCurrentPlayer().getOpponent().getPlayerKing().getPosition(), transitionBoard.getCurrentPlayer().getLegalMoves());
        if (!kingAttacks.isEmpty()) {
            return new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK);
        }
        return new MoveTransition(this.board, move, MoveStatus.DONE);
    }

    public player(Board board, Collection<Move> legalMoves, Collection<Move> opponentMove) {
        this.board = board;
        this.isInCheck = !player.calculateAttackMove(this.playerKing.getPosition(), opponentMove).isEmpty();
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
    }

    private static Collection<Move> calculateAttackMove(int position, Collection<Move> moves) {
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
