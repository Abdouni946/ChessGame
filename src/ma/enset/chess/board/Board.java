package ma.enset.chess.board;

import com.google.common.collect.ImmutableList;
import ma.enset.chess.Alliance;
import ma.enset.chess.pieces.*;
import ma.enset.chess.player.BlackPlayer;
import ma.enset.chess.player.WhitePlayer;
import ma.enset.chess.player.player;

import java.util.*;

public class Board {
    private final List<Tile> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final player currentPlayer;

    public Collection<Piece> getWhitePieces() {
        return whitePieces;
    }

    public Collection<Piece> getBlackPieces() {
        return blackPieces;
    }

    //Constructor
    private Board(final Builder builder) {
        this.gameBoard = createGameBoard(builder);
        this.currentPlayer = null;
        this.whitePieces = calculateActivePieces(gameBoard, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(gameBoard, Alliance.BLACK);

        final Collection<Move> whiteLegalMoves = calcLegalMoves(this.whitePieces);
        final Collection<Move> blackLegalMoves = calcLegalMoves(this.blackPieces);

        this.whitePlayer = new WhitePlayer(this, whiteLegalMoves, blackLegalMoves);// for legal castle moves
        this.blackPlayer = new BlackPlayer(this, whiteLegalMoves, blackLegalMoves);

    }

    public player blackPlayer() {
        return this.blackPlayer;
    }

    public player whitePlayer() {
        return this.whitePlayer;
    }

    public player getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public String toString() {
        final StringBuilder BoardText = new StringBuilder();// mutable list of characters
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            final String tileText = this.gameBoard.get(i).toString();
            BoardText.append(String.format("%3s", tileText));
            if ((i + 1) % BoardUtils.NUM_TILES_PER_ROW == 0) {
                BoardText.append("\n");
            }
        }
        return BoardText.toString();
    }

    private Collection<Move> calcLegalMoves(final Collection<Piece> Pieces) {

        final List<Move> legalMoves = new ArrayList<>();
        for (final Piece piece : Pieces) {
            legalMoves.addAll(piece.calcLegalMoves(this)); // calculate legal moves for each piece
        }

        return ImmutableList.copyOf(legalMoves);
    }

    private static Collection<Piece> calculateActivePieces(final List<Tile> gameBoard, final Alliance alliance) {
        final List<Piece> activatePieces = new ArrayList<>();
        for (final Tile tile : gameBoard) {
            if (tile.isOccupied()) {
                final Piece piece = tile.getPiece();
                if (piece.getAlliance() == alliance) {
                    activatePieces.add(piece);
                }
            }
        }
        return ImmutableList.copyOf(activatePieces);
    }

    public Tile getTile(final int coordinates) {
        return gameBoard.get(coordinates);
    }

    private static List<Tile> createGameBoard(final Builder builder) {
        final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            tiles[i] = Tile.createTile(i, builder.BoardConfig.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    public static Board CreateStandardBoard() {
        final Builder builder = new Builder();
        //Black Layout
        builder.setPiece(new Rook(0, Alliance.BLACK));
        builder.setPiece(new Knight(1, Alliance.BLACK));
        builder.setPiece(new Bishop(2, Alliance.BLACK));
        builder.setPiece(new Queen(3, Alliance.BLACK));
        builder.setPiece(new King(4, Alliance.BLACK));
        builder.setPiece(new Bishop(5, Alliance.BLACK));
        builder.setPiece(new Knight(6, Alliance.BLACK));
        builder.setPiece(new Rook(7, Alliance.BLACK));
        builder.setPiece(new Pawn(8, Alliance.BLACK));
        builder.setPiece(new Pawn(9, Alliance.BLACK));
        builder.setPiece(new Pawn(10, Alliance.BLACK));
        builder.setPiece(new Pawn(11, Alliance.BLACK));
        builder.setPiece(new Pawn(12, Alliance.BLACK));
        builder.setPiece(new Pawn(13, Alliance.BLACK));
        builder.setPiece(new Pawn(14, Alliance.BLACK));
        builder.setPiece(new Pawn(15, Alliance.BLACK));
        //White Layout
        builder.setPiece(new Pawn(48, Alliance.WHITE));
        builder.setPiece(new Pawn(49, Alliance.WHITE));
        builder.setPiece(new Pawn(50, Alliance.WHITE));
        builder.setPiece(new Pawn(51, Alliance.WHITE));
        builder.setPiece(new Pawn(52, Alliance.WHITE));
        builder.setPiece(new Pawn(53, Alliance.WHITE));
        builder.setPiece(new Pawn(54, Alliance.WHITE));
        builder.setPiece(new Pawn(55, Alliance.WHITE));
        builder.setPiece(new Rook(56, Alliance.WHITE));
        builder.setPiece(new Knight(57, Alliance.WHITE));
        builder.setPiece(new Bishop(58, Alliance.WHITE));
        builder.setPiece(new Queen(59, Alliance.WHITE));
        builder.setPiece(new King(60, Alliance.WHITE));
        builder.setPiece(new Bishop(61, Alliance.WHITE));
        builder.setPiece(new Knight(62, Alliance.WHITE));
        builder.setPiece(new Rook(63, Alliance.WHITE));
        //White to move
        builder.setMoveMaker(Alliance.WHITE);
        return builder.build();
    }

    //The Builder Pattern allows you to vary the chess game's setup
    public static class Builder {
        Map<Integer, Piece> BoardConfig;
        Alliance nextMoveMaker;

        public Builder() {
            this.BoardConfig = new HashMap<>();
        }

        public Builder setPiece(final Piece piece) {
            this.BoardConfig.put(piece.getPosition(), piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public Board build() {  // when we invoke build , it will create a new board
            return new Board(this);
        }
    }

}
