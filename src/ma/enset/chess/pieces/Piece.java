package ma.enset.chess.pieces;

import ma.enset.chess.Alliance;
import ma.enset.chess.board.Board;
import ma.enset.chess.board.Move;

import java.util.Collection;

public abstract class Piece {
    protected final int position;
    protected final Alliance alliance;

    Piece(final int position, final Alliance alliance) {
        this.position = position;
        this.alliance = alliance;
    }

    public int getPosition() {
        return position;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public abstract Collection<Move> calcLegalMoves(final Board board);
}
