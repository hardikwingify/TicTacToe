package example.tictactoe.models;

/**
 * Created by hardik on 07/07/16.
 */
public class Point {
    public Point(float startX, float startY, float endX, float endY, int cellNumber) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.cellNumber = cellNumber;
    }

    float startX, startY, endX, endY;
    int cellNumber;

    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }

    public float getEndX() {
        return endX;
    }

    public float getEndY() {
        return endY;
    }

    public int getCellNumber() {
        return cellNumber;
    }
}
