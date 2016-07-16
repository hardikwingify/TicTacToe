package example.tictactoe.models;

/**
 * Created by 638 on 7/16/2016.
 */
public class Move {
    private String playerId;
    private int xPosition;
    private int yPosition;
    private String moveTimeStamp;

    public Move() {
    }

    public Move(String playerId, int xPosition, int yPosition, String moveTimeStamp) {
        this.playerId = playerId;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.moveTimeStamp = moveTimeStamp;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public String getMoveTimeStamp() {
        return moveTimeStamp;
    }

    public void setMoveTimeStamp(String moveTimeStamp) {
        this.moveTimeStamp = moveTimeStamp;
    }
}
