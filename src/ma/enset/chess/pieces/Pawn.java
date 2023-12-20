package ma.enset.chess.pieces;

import com.google.common.collect.ImmutableList;
import ma.enset.chess.Alliance;
import ma.enset.chess.board.Board;
import ma.enset.chess.board.BoardUtils;
import ma.enset.chess.board.Move;
import ma.enset.chess.board.Move.MajorMove;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {
    private final static int[] LEGAL_MOVES_OFFSETS = { 8 };
    Pawn(final int position, final Alliance alliance) {
        super(position, alliance);
    }

    @Override
    public Collection<Move> calcLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateOffset : LEGAL_MOVES_OFFSETS) {
            final int destinationCoordinate = this.position + (this.getAlliance().getDirection() * candidateOffset);
            if (!BoardUtils.isValidTileCoor(destinationCoordinate)) {
                continue;
            }
            if (candidateOffset == 8 && !board.getTile(destinationCoordinate).isOccupied()) {
                // Needs to be changed !!
                legalMoves.add(new MajorMove(board, this, destinationCoordinate));
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
}
