package ma.enset.chess.pieces;

import ma.enset.chess.Alliance;
import ma.enset.chess.board.Board;
import ma.enset.chess.board.Move;

import java.util.Collection;

public abstract class Piece {
    protected final int position;
    protected final Alliance alliance;
    protected final boolean isFirstMove;

    Piece(final int position, final Alliance alliance) {
        this.position = position;
        this.alliance = alliance;
        this.isFirstMove = false;
    }

    public int getPosition() {
        return this.position;
    }

    public Alliance getAlliance() {
        return this.alliance;
    }

    public boolean isFirstMove() { return this.isFirstMove; }

    public abstract Collection<Move> calcLegalMoves(final Board board);
}
