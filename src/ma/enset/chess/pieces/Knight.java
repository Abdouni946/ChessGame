package ma.enset.chess.pieces;

import com.google.common.collect.ImmutableList;
import ma.enset.chess.Alliance;
import ma.enset.chess.board.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {
    private final static int[] LEGAL_MOVES_OFFSETS = { -17, -15, -10, -6, 6, 10, 15, 17 };

    Knight(final int position, final Alliance alliance) {
        super(position, alliance);
    }

    @Override
    public Collection<Move> calcLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateOffset : LEGAL_MOVES_OFFSETS) {
            final int destinationCoordinates = this.position + candidateOffset;
            if(BoardUtils.isValidTileCoor(destinationCoordinates)) {
                if (isFirstColExclusion(this.position, candidateOffset) || isSecondColExclusion(this.position, candidateOffset) || isSeventhColExclusion(this.position, candidateOffset) || isEighthColExclusion(this.position, candidateOffset)) {
                    continue;
                }
                final Tile destinationTile = board.getTile(destinationCoordinates);
                if(!destinationTile.isOccupied()) {
                    legalMoves.add(new MajorMove());
                } else {
                    final Piece occupingPiece = destinationTile.getPiece();
                    final Alliance occupingPieceAlliance = occupingPiece.getAlliance();
                    if (this.alliance != occupingPieceAlliance) {
                        legalMoves.add(new AttackMove());
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColExclusion(final int currentPos, final int offset) {
        return BoardUtils.FIRST_COL[currentPos] && ((offset == -17) || (offset == -10) || (offset == 6) || (offset == 15));
    }
    private static boolean isSecondColExclusion(final int currentPos, final int offset) {
        return BoardUtils.SECOND_COL[currentPos] && ((offset == -10) || (offset == 6));
    }
    private static boolean isSeventhColExclusion(final int currentPos, final int offset) {
        return BoardUtils.SEVENTH_COL[currentPos] && ((offset == -6) || (offset == 10));
    }
    private static boolean isEighthColExclusion(final int currentPos, final int offset) {
        return BoardUtils.EIGHTH_COL[currentPos] && ((offset == -15) || (offset == -6) || (offset == 10) || (offset == 17));
    }
}