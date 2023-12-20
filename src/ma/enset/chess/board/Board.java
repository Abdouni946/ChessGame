package ma.enset.chess.board;

import com.google.common.collect.ImmutableList;
import ma.enset.chess.Alliance;
import ma.enset.chess.pieces.Piece;

import java.util.List;
import java.util.Map;
public class Board {

    private final List<Tile> gameBoard;

    //Constructor
    private Board(Builder builder) {
        this.gameBoard = createGameBoard(builder);
    }
    public Tile getTile(final int coordinates) {
        return null;
    }
    private static List<Tile> createGameBoard(final Builder builder) {
        final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
        for(int i=0 ;i < BoardUtils.NUM_TILES;i++){
            tiles[i] = Tile.createTile(i,builder.BoardConfig.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }


    //The Builder Pattern allows you to vary the chess game's setup
    public static class Builder {

        Map<Integer, Piece> BoardConfig ;
        Alliance nextMoveMaker;
        public Builder ( ) {

        }
        public Builder setPiece(final Piece piece){
            this.BoardConfig.put(piece.getPosition(),piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance nextMoveMaker){
            this.nextMoveMaker=nextMoveMaker;
            return this;
        }

        public Board build (){  // when we invoke build , it will create a new board
            return new  Board(this);
        }

    }

}
