package ma.enset.chess.board;

public class BoardUtils {
    public static final boolean[] FIRST_COL = null;
    public static final boolean[] SECOND_COL = null;
    public static final boolean[] SEVENTH_COL = null;
    public static final boolean[] EIGHTH_COL = null;

    private BoardUtils() {
        throw new RuntimeException("BoardUtils can't be instantiated");
    }
    public static boolean isValidTileCoor(final int coordinates) {
        return coordinates >= 0 && coordinates < 64;
    }
}
