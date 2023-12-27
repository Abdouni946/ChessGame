package ma.enset.chess.engine.pieces;

import com.google.common.collect.ImmutableList;
import ma.enset.chess.engine.Alliance;
import ma.enset.chess.engine.board.Board;
import ma.enset.chess.engine.board.BoardUtils;
import ma.enset.chess.engine.board.Move;
import ma.enset.chess.engine.board.Move.AttackMove;
import ma.enset.chess.engine.board.Move.MajorMove;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {
    private final static int[] LEGAL_MOVES_OFFSETS = {7, 8, 9, 16};

    public Pawn(final int position, final Alliance alliance) {
        super(position, pieceType.Pawn, alliance);
    }

    @Override
    public String toString() {
        return pieceType.Pawn.toString();
    }

    @Override
    public Piece movePiece(final Move move) {
        return new Pawn(move.getDestinationCoordinate(), move.getMovedPiece().getAlliance());
    }

    @Override
    public Collection<Move> calcLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateOffset : LEGAL_MOVES_OFFSETS) {
            final int destinationCoordinate = this.position + (this.alliance.getDirection() * candidateOffset);
            if (!BoardUtils.isValidTileCoor(destinationCoordinate)) {
                continue;
            }
            if (candidateOffset == 8 && !board.getTile(destinationCoordinate).isOccupied()) { // Non attacking Pawn move
                // Needs to be changed !!
                legalMoves.add(new MajorMove(board, this, destinationCoordinate));
            } else if (candidateOffset == 16 &&
                    this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.position] && this.alliance.isBlack()) ||
                    (BoardUtils.SEVENTH_ROW[this.position] && this.alliance.isWhite())) { // Pawn jump
                final int behindDestinationCoor = this.position + (this.alliance.getDirection() * 8);
                if (!board.getTile(behindDestinationCoor).isOccupied() && !board.getTile(destinationCoordinate).isOccupied()) {
                    // Needs to be changed !!
                    legalMoves.add(new MajorMove(board, this, destinationCoordinate));
                }
            } else if (candidateOffset == 7 &&
                    !((BoardUtils.EIGHTH_COL[this.position] && this.alliance.isWhite()) ||
                            (BoardUtils.FIRST_COL[this.position] && this.alliance.isBlack()))) { // Attacking pawn move 1
                if (board.getTile(destinationCoordinate).isOccupied()) {
                    final Piece occupingPiece = board.getTile(destinationCoordinate).getPiece();
                    if (this.alliance != occupingPiece.getAlliance()) {
                        // Needs to be changed !!
                        legalMoves.add(new AttackMove(board, this, destinationCoordinate, occupingPiece));
                    }
                }
            } else if (candidateOffset == 9 &&
                    !((BoardUtils.FIRST_COL[this.position] && this.alliance.isWhite()) ||
                            (BoardUtils.EIGHTH_COL[this.position] && this.alliance.isBlack()))) { // Attacking pawn move 2
                if (board.getTile(destinationCoordinate).isOccupied()) {
                    final Piece occupingPiece = board.getTile(destinationCoordinate).getPiece();
                    if (this.alliance != occupingPiece.getAlliance()) {
                        // Needs to be changed !!
                        legalMoves.add(new AttackMove(board, this, destinationCoordinate, occupingPiece));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
}
