package ma.enset.chess.player;

import ma.enset.chess.Alliance;
import ma.enset.chess.board.Board;
import ma.enset.chess.board.Move;
import ma.enset.chess.pieces.Piece;

import java.util.Collection;

public class BlackPlayer extends player {
    public BlackPlayer(final Board board,
                       final Collection<Move> whiteLegalMoves,
                       final Collection<Move> blackLegalMoves) {

        super(board, blackLegalMoves, whiteLegalMoves);

    }

    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public player getOpponent() {
        return this.board.whitePlayer();
    }

}
