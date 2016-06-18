package example.tictactoe.models;

import java.util.ArrayList;

/**
 * Created by hardik on 18/06/16.
 */
public class Circle {
    ArrayList<Integer> listOfMoves = new ArrayList<>();

    public void addMoveBox(int blockNumber) {
        listOfMoves.add(blockNumber);
    }

    public void clearMoves() {
        listOfMoves.clear();
    }
}
