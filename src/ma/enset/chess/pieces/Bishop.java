package ma.enset.chess.pieces;

import com.google.common.collect.ImmutableList;
import ma.enset.chess.Alliance;
import ma.enset.chess.board.Board;
import ma.enset.chess.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bishop extends Piece {
    private final static int[] LEGAL_MOVES_OFFSETS = { -9, -7, +7, +9 };

    Bishop(final int position, final Alliance alliance) { super(position, alliance); }

    @Override
    public Collection<Move> calcLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        return ImmutableList.copyOf(legalMoves);
    }
}
