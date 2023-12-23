package ma.enset.chess.pieces;

import com.google.common.collect.ImmutableList;
import ma.enset.chess.Alliance;
import ma.enset.chess.board.Board;
import ma.enset.chess.board.BoardUtils;
import ma.enset.chess.board.Move;
import ma.enset.chess.board.Move.AttackMove;
import ma.enset.chess.board.Move.MajorMove;
import ma.enset.chess.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {
    private final static int[] LEGAL_MOVES_OFFSETS = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final int position, final Alliance alliance) {
        super(position, alliance);
    }

    @Override
    public Collection<Move> calcLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateOffset : LEGAL_MOVES_OFFSETS) {
            final int destinationCoordinate = this.position + candidateOffset;
            if (BoardUtils.isValidTileCoor(destinationCoordinate)) {
                if (isFirstColExclusion(this.position, candidateOffset) || isSecondColExclusion(this.position, candidateOffset) || isSeventhColExclusion(this.position, candidateOffset) || isEighthColExclusion(this.position, candidateOffset)) {
                    continue;
                }
                final Tile destinationTile = board.getTile(destinationCoordinate);
                if (!destinationTile.isOccupied()) {
                    legalMoves.add(new MajorMove(board, this, destinationCoordinate));
                } else {
                    final Piece occupingPiece = destinationTile.getPiece();
                    final Alliance occupingPieceAlliance = occupingPiece.getAlliance();
                    if (this.alliance != occupingPieceAlliance) {
                        legalMoves.add(new AttackMove(board, this, destinationCoordinate, occupingPiece));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public String toString() {
        return pieceType.Knight.toString();
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