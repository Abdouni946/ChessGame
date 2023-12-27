package ma.enset.chess.engine.pieces;

import com.google.common.collect.ImmutableList;
import ma.enset.chess.engine.Alliance;
import ma.enset.chess.engine.board.Board;
import ma.enset.chess.engine.board.BoardUtils;
import ma.enset.chess.engine.board.Move;
import ma.enset.chess.engine.board.Move.AttackMove;
import ma.enset.chess.engine.board.Move.MajorMove;
import ma.enset.chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece {
    private final static int[] LEGAL_MOVES_VECTOR_OFFSETS = {-8, -1, +1, +8};

    public Rook(final int position, final Alliance alliance) {
        super(position, pieceType.Rook, alliance);
    }

    @Override
    public String toString() {
        return pieceType.Rook.toString();
    }

    @Override
    public Piece movePiece(final Move move) {
        return new Rook(move.getDestinationCoordinate(), move.getMovedPiece().getAlliance());
    }

    @Override
    public Collection<Move> calcLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateOffset : LEGAL_MOVES_VECTOR_OFFSETS) {
            int destinationCoordinate = this.position;
            while (BoardUtils.isValidTileCoor(destinationCoordinate)) {
                if (isFirstColExclusion(destinationCoordinate, candidateOffset) || isEighthColExclusion(destinationCoordinate, candidateOffset)) {
                    break;
                }
                destinationCoordinate += candidateOffset;
                if (BoardUtils.isValidTileCoor(destinationCoordinate)) {
                    final Tile destinationTile = board.getTile(destinationCoordinate);
                    if (!destinationTile.isOccupied()) {
                        legalMoves.add(new MajorMove(board, this, destinationCoordinate));
                    } else {
                        final Piece occupingPiece = destinationTile.getPiece();
                        final Alliance occupingPieceAlliance = occupingPiece.getAlliance();
                        if (this.alliance != occupingPieceAlliance) {
                            legalMoves.add(new AttackMove(board, this, destinationCoordinate, occupingPiece));
                        }
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColExclusion(final int currentPos, final int offset) {
        return BoardUtils.FIRST_COL[currentPos] && (offset == -1);
    }

    private static boolean isEighthColExclusion(final int currentPos, final int offset) {
        return BoardUtils.EIGHTH_COL[currentPos] && (offset == 1);
    }
}
