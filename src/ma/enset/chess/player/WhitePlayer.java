package ma.enset.chess.player;

import ma.enset.chess.Alliance;
import ma.enset.chess.board.Board;
import ma.enset.chess.board.Move;
import ma.enset.chess.pieces.Piece;

import java.util.Collection;

public class WhitePlayer extends player {

    public WhitePlayer(Board board,
                       Collection<Move> whiteLegalMoves,
                       Collection<Move> blackLegalMoves) {
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
