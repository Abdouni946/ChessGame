package ma.enset.chess.pieces;

import ma.enset.chess.Alliance;
import ma.enset.chess.board.Move;

import java.util.List;

public abstract class Piece {
    protected final int position;
    protected final Alliance alliance;

    Piece(final int position, final Alliance alliance) {
        this.position = position;
        this.alliance = alliance;
    }

    public abstract List<Move> calcMoves(final Board board);
}
