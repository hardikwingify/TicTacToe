package example.tictactoe.models;

import java.util.ArrayList;

/**
 * Created by hardik on 18/06/16.
 */
public class Cross extends ValidateMove {
    ArrayList<Integer> listOfMoves = new ArrayList<>();
    int arrayOfMoves[];
    final int selected = 1;

    public Cross() {
        arrayOfMoves = new int[9];
    }

    public void addMoveBox(int blockNumber) {
        listOfMoves.add(blockNumber);
        arrayOfMoves[blockNumber] = 1;
        isTicTacToeCreated();
    }

    public void clearMoves() {
        listOfMoves.clear();
    }

    @Override
    boolean isTicTacToeCreated() {
        boolean isGameWon = arrayOfMoves[0] == selected
                && arrayOfMoves[1] == selected
                && arrayOfMoves[2] == selected
                ||
                arrayOfMoves[0] == selected // 3-in-the-column
                        && arrayOfMoves[1] == selected
                        && arrayOfMoves[2] == selected
                ||
                arrayOfMoves[0] == selected
                        && arrayOfMoves[1] == selected
                        && arrayOfMoves[2] == selected
                ||
                arrayOfMoves[2] == selected
                        && arrayOfMoves[1] == selected
                        && arrayOfMoves[0] == selected;
        return isGameWon;
    }
}
