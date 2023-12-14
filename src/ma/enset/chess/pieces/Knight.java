package ma.enset.chess.pieces;

import com.google.common.collect.ImmutableList;
import ma.enset.chess.Alliance;
import ma.enset.chess.board.Board;
import ma.enset.chess.board.BoardUtils;
import ma.enset.chess.board.Move;
import ma.enset.chess.board.Tile;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    private final static int[] LEGAL_MOVES_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17 };

    Knight(final int position, final Alliance alliance) {
        super(position, alliance);
    }

    @Override
    public List<Move> calcLegalMoves(Board board) {
        int destinationCoordinates;
        final List<Move> legalMoves = new ArrayList<>();
        for (final int legalMoveCoordinates : LEGAL_MOVES_COORDINATES) {
            destinationCoordinates = this.position + legalMoveCoordinates;
            if(BoardUtils.isValidTileCoor(destinationCoordinates)) {
                final Tile destinationTile = board.getTile(destinationCoordinates);
                if(!destinationTile.isOccupied()) {
                    legalMoves.add(new Move());
                } else {
                    final Piece occupingPiece = destinationTile.getPiece();
                    final Alliance occupingPieceAlliance = occupingPiece.getAlliance();
                    if (this.alliance != occupingPieceAlliance) {
                        legalMoves.add(new Move());
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
}
