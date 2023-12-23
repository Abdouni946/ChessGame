package ma.enset.chess.pieces;

import ma.enset.chess.Alliance;
import ma.enset.chess.board.Board;
import ma.enset.chess.board.Move;

import java.util.Collection;

public abstract class Piece {
    protected final int position;
    protected final pieceType piecesType;
    protected final Alliance alliance;
    protected final boolean isFirstMove;

    public Piece(final int position, pieceType piecesType, final Alliance alliance) {
        this.position = position;
        this.piecesType = piecesType;
        this.alliance = alliance;
        this.isFirstMove = false;
    }

    public int getPosition() {
        return this.position;
    }
    public pieceType getPieceType() {
        return this.piecesType;
    }

    public Alliance getAlliance() {
        return this.alliance;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public abstract Collection<Move> calcLegalMoves(final Board board);

    public enum pieceType {

        Pawn("P") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        Rook("R") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        Queen("Q") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        King("K") {
            @Override
            public boolean isKing() {
                return true;
            }
        },
        Bishop("B") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        Knight("N") {
            @Override
            public boolean isKing() {
                return false;
            }
        };
        public abstract boolean isKing();
        private String pieceName;

        pieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }


    }
}
