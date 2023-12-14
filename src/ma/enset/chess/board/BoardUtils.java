package ma.enset.chess.board;

public class BoardUtils {
    public static boolean isValidTileCoor(final int coordinates) {
        return coordinates >= 0 && coordinates <= 62;
    }
}
