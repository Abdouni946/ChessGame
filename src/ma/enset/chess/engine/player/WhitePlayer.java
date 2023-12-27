package ma.enset.chess.engine.player;

import ma.enset.chess.engine.Alliance;
import ma.enset.chess.engine.board.Board;
import ma.enset.chess.engine.board.Move;
import ma.enset.chess.engine.pieces.Piece;

import java.util.Collection;

public class WhitePlayer extends player {

    public WhitePlayer(final Board board,
                       final Collection<Move> whiteLegalMoves,
                       final Collection<Move> blackLegalMoves) {
        super(board, whiteLegalMoves, blackLegalMoves);
    }

    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public player getOpponent() {
        return this.board.blackPlayer();
    }

}
