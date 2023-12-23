package ma.enset.chess.board;

public class BoardUtils {
    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;

    public static final boolean[] FIRST_COL = initCol(0);
    public static final boolean[] SECOND_COL = initCol(1);

    public static final boolean[] SEVENTH_COL = initCol(6);
    public static final boolean[] EIGHTH_COL = initCol(7);

    public static final boolean[] SECOND_ROW = initRow(1);
    public static final boolean[] SEVENTH_ROW = initRow(6);

    private BoardUtils() {
        throw new RuntimeException("BoardUtils can't be instantiated");
    }

    private static boolean[] initCol(int colNumber) {
        final boolean[] col = new boolean[NUM_TILES];
        do {
            col[colNumber] = true;
            colNumber += NUM_TILES_PER_ROW;
        } while (colNumber < NUM_TILES);
        return col;
    }

    private static boolean[] initRow(int rowNumber) {
        final boolean[] row = new boolean[NUM_TILES];
        do {
            row[rowNumber] = true;
            ++rowNumber;
        } while (rowNumber % NUM_TILES_PER_ROW != 0);
        return row;
    }

    public static boolean isValidTileCoor(final int coordinates) {
        return coordinates >= 0 && coordinates < NUM_TILES;
    }
}
