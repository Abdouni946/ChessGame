package ma.enset.chess.board;

public class BoardUtils {
    private BoardUtils() {
        throw new RuntimeException("BoardUtils can't be instantiated");
    }
    public static boolean isValidTileCoor(final int coordinates) {
        return coordinates >= 0 && coordinates <= 62;
    }
}
