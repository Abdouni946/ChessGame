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
    private final int cachedHashCode;

    public Piece(final int position, pieceType piecesType, final Alliance alliance) {
        this.position = position;
        this.piecesType = piecesType;
        this.alliance = alliance;
        this.isFirstMove = false;
        this.cachedHashCode = computeHashCode();
    }

    private int computeHashCode() {
        int result = piecesType.hashCode();
        result = 31 * result + alliance.hashCode();
        result = 31 * result + position;
        result = 31 * result + (isFirstMove ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Piece)) {
            return false;
        }
        final Piece otherPiece = (Piece) other;
        return position == otherPiece.getPosition() && piecesType == otherPiece.getPieceType() &&
                alliance == otherPiece.getAlliance() && isFirstMove == otherPiece.isFirstMove();
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
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

    public abstract Piece movePiece(Move move);

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
