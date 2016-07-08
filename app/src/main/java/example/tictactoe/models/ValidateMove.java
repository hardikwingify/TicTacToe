package example.tictactoe.models;

/**
 * Created by hardik on 18/06/16.
 */
public abstract class ValidateMove {

    int arrayOfMoves[];
    final int selected = 1;
    public static final int HORIZONTAL_FIRST_LINE = 1;
    public static final int HORIZONTAL_SECOND_LINE = 2;
    public static final int HORIZONTAL_THIRD_LINE = 3;
    public static final int VERTICAL_FIRST_LINE = 4;
    public static final int VERTICAL_SECOND_LINE = 5;
    public static final int VERTICAL_THIRD_LINE = 6;
    public static final int DIAGONAL_FIRST_LINE = 7;
    public static final int DIAGONAL_SECOND_LINE = 8;

    int isTicTacToeCreated() {
        boolean isGameWon = arrayOfMoves[0] == selected
                && arrayOfMoves[1] == selected
                && arrayOfMoves[2] == selected;

        if (isGameWon) {
            return HORIZONTAL_FIRST_LINE;
        }
        isGameWon = arrayOfMoves[3] == selected
                && arrayOfMoves[4] == selected
                && arrayOfMoves[5] == selected;
        if (isGameWon) {
            return HORIZONTAL_SECOND_LINE;
        }
        isGameWon = arrayOfMoves[6] == selected
                && arrayOfMoves[7] == selected
                && arrayOfMoves[8] == selected;
        if (isGameWon) {
            return HORIZONTAL_THIRD_LINE;
        }
        isGameWon = arrayOfMoves[0] == selected
                && arrayOfMoves[3] == selected
                && arrayOfMoves[6] == selected;
        if (isGameWon) {
            return VERTICAL_FIRST_LINE;
        }
        isGameWon = arrayOfMoves[1] == selected
                && arrayOfMoves[4] == selected
                && arrayOfMoves[7] == selected;
        if (isGameWon) {
            return VERTICAL_SECOND_LINE;
        }
        isGameWon = arrayOfMoves[2] == selected
                && arrayOfMoves[5] == selected
                && arrayOfMoves[8] == selected;

        if (isGameWon) {
            return VERTICAL_THIRD_LINE;
        }
        isGameWon = arrayOfMoves[0] == selected
                && arrayOfMoves[4] == selected
                && arrayOfMoves[8] == selected;

        if (isGameWon) {
            return DIAGONAL_FIRST_LINE;
        }

        isGameWon = arrayOfMoves[2] == selected
                && arrayOfMoves[4] == selected
                && arrayOfMoves[6] == selected;
        if (isGameWon) {
            return DIAGONAL_SECOND_LINE;
        }
        return -1;
    }
}
