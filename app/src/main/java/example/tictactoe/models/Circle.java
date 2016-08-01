package example.tictactoe.models;

import java.util.ArrayList;

/**
 * Created by hardik on 18/06/16.
 */
public class Circle extends ValidateMove {

    private ArrayList<Point> listOfMoves = new ArrayList<>();

    public Circle() {
        arrayOfMoves = new boolean[9];
    }

    public int addMoveBox(int blockNumber, float startX, float startY, float endX, float endY) {
        listOfMoves.add(new Point(startX, startY, endX, endY, blockNumber));
        arrayOfMoves[blockNumber] = true;
        return isTicTacToeCreated();
    }

    public ArrayList<Point> getMoves() {
        return listOfMoves;
    }

    public void clearMoves() {
        listOfMoves.clear();
        for (int i = 0; i < arrayOfMoves.length; i++) {
            arrayOfMoves[i] = false;
        }
    }

}
