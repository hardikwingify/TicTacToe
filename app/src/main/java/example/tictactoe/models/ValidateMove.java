package example.tictactoe.models;

/**
 * Created by hardik on 18/06/16.
 */
public abstract class ValidateMove {

    int arrayOfMoves[];
    final int selected = 1;

    boolean isTicTacToeCreated() {
        boolean isGameWon = arrayOfMoves[0] == selected
                && arrayOfMoves[1] == selected
                && arrayOfMoves[2] == selected;

        if (isGameWon) {
            return isGameWon;
        }
        isGameWon = arrayOfMoves[3] == selected
                && arrayOfMoves[4] == selected
                && arrayOfMoves[5] == selected;
        if (isGameWon) {
            return isGameWon;
        }
        isGameWon = arrayOfMoves[6] == selected
                && arrayOfMoves[7] == selected
                && arrayOfMoves[8] == selected;
        if (isGameWon) {
            return isGameWon;
        }
        isGameWon = arrayOfMoves[0] == selected
                && arrayOfMoves[3] == selected
                && arrayOfMoves[6] == selected;
        if (isGameWon) {
            return isGameWon;
        }
        isGameWon = arrayOfMoves[1] == selected
                && arrayOfMoves[4] == selected
                && arrayOfMoves[7] == selected;
        if (isGameWon) {
            return isGameWon;
        }
        isGameWon = arrayOfMoves[2] == selected
                && arrayOfMoves[5] == selected
                && arrayOfMoves[8] == selected;

        if (isGameWon) {
            return isGameWon;
        }
        isGameWon = arrayOfMoves[0] == selected
                && arrayOfMoves[4] == selected
                && arrayOfMoves[8] == selected;

        if (isGameWon) {
            return isGameWon;
        }

        isGameWon = arrayOfMoves[2] == selected
                && arrayOfMoves[4] == selected
                && arrayOfMoves[6] == selected;
        if (isGameWon) {
            return isGameWon;
        }
        return false;
    }
}
