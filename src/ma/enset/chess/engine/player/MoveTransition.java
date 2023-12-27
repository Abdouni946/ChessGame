package ma.enset.chess.engine.player;

import ma.enset.chess.engine.board.Board;
import ma.enset.chess.engine.board.Move;

public class MoveTransition {
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveTransition(final Board transitionBoard,
                          final Move move,
                          final MoveStatus moveStatus) {
        this.move = move;
        this.transitionBoard = transitionBoard;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }
}
