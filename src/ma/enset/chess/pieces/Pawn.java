package ma.enset.chess.pieces;

import ma.enset.chess.Alliance;
import ma.enset.chess.board.Board;
import ma.enset.chess.board.Move;

import java.util.Collection;

public class Pawn extends Piece {
    Pawn(final int position, final Alliance alliance) {
        super(position, alliance);
    }

    @Override
    public Collection<Move> calcLegalMoves(final Board board) {
        return null;
    }
}
