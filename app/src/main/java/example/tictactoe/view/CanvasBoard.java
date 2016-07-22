package example.tictactoe.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import example.tictactoe.R;
import example.tictactoe.models.Circle;
import example.tictactoe.models.Cross;
import example.tictactoe.models.Point;
import example.tictactoe.models.ValidateMove;

/**
 * Created by hardik on 18/06/16.
 */
public class CanvasBoard extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {


    //CanvasThread canvasThread;
    int boardWidth, boardHeight;
    Paint boardPaint, crossPaint, circlePaint, winningLinePaint;
    float mX, mY;
    int arrayOfMoves[];
    int halfWidth;
    int halfHeight;
    boolean isCrossSelected = false;
    final int LEFT_TOP = 0;
    final int LEFT_MIDDLE = 3;
    final int LEFT_BOTTOM = 6;
    final int CENTER_TOP = 1;
    final int CENTER_MIDDLE = 4;
    final int CENTER_BOTTOM = 7;
    final int RIGHT_TOP = 2;
    final int RIGHT_MIDDLE = 5;
    final int RIGHT_BOTTOM = 8;
    Context context;
    Cross cross;
    Circle circle;
    int EMPTY_CELL = -1;
    Bitmap crossBitmap, circleBitmap;
    int isGameWon;
    int extremeLeft;
    Point winningLinePoint;

    public CanvasBoard(Context context, int boardWidth, int boardHeight) {
        super(context);
        this.context = context;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        halfWidth = boardWidth / 2;
        halfHeight = boardHeight / 2;
        getHolder().addCallback(this);
        arrayOfMoves = new int[]{EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL};
        cross = new Cross();
        circle = new Circle();
        setOnTouchListener(this);
        crossBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cross);
        circleBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.circle);
    }

    public CanvasBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setWillNotDraw(false); // I will draw on my own
        //canvasThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //canvasThread.setRunning(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initialiseDrawBoard(canvas);
        for (Point cellNumber : circle.getMoves()) {
            float centerX = (cellNumber.getStartX() + cellNumber.getEndX()) / 2;
            float centerY = (cellNumber.getEndY() + cellNumber.getStartY()) / 2;
            canvas.drawCircle(centerX, centerY, 100f, circlePaint);
        }

        for (Point cellNumber : cross.getMoves()) {
            canvas.drawLine(cellNumber.getStartX(), cellNumber.getStartY(), cellNumber.getEndX(), cellNumber.getEndY(), crossPaint);
            canvas.drawLine(cellNumber.getEndX(), cellNumber.getStartY(), cellNumber.getStartX(), cellNumber.getEndY(), crossPaint);
        }
        if (isGameWon != -1 && isGameWon != 0) {
            canvas.drawLine(winningLinePoint.getStartX(), winningLinePoint.getStartY(), winningLinePoint.getEndX(), winningLinePoint.getEndY(), winningLinePaint);
        }
        //canvas.drawColor(Color.WHITE);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mX = event.getX();
                mY = event.getY();

                if (mX < boardWidth / 3) { //Top left
                    if (mY < boardHeight / 3 && arrayOfMoves[0] != 1) {
                        makeMove(LEFT_TOP, 40, 40, boardWidth / 3 - 40, boardHeight / 3 - 40);
                        arrayOfMoves[0] = 1;
                    } else if (mY > 2 * (boardHeight / 3) && arrayOfMoves[6] != 1) {
                        makeMove(LEFT_BOTTOM, 40, 2 * (boardHeight / 3) + 40, boardWidth / 3 - 40, boardHeight - 40);
                        arrayOfMoves[6] = 1;
                    } else if (mY < 2 * (boardHeight / 3) && arrayOfMoves[3] != 1) { // Middle
                        makeMove(LEFT_MIDDLE, 40, (boardHeight / 3) + 40, boardWidth / 3 - 40, 2 * (boardHeight / 3) - 40);
                        arrayOfMoves[3] = 1;
                    }
                } else if (mX < 2 * (boardWidth / 3)) {
                    if (mY < boardHeight / 3 && arrayOfMoves[1] != 1) {
                        makeMove(CENTER_TOP, boardWidth / 3 + 40, 40, 2 * (boardWidth / 3) - 40, boardHeight / 3 - 40);
                        arrayOfMoves[1] = 1;
                    } else if (mY > 2 * (boardHeight / 3) && arrayOfMoves[7] != 1) {
                        arrayOfMoves[7] = 1;
                        makeMove(CENTER_BOTTOM, boardWidth / 3 + 40, 2 * (boardHeight / 3) + 40, 2 * (boardWidth / 3) - 40, boardHeight - 40);
                    } else if (mY < 2 * (boardHeight / 3) && arrayOfMoves[4] != 1) {
                        arrayOfMoves[4] = 1;
                        makeMove(CENTER_MIDDLE, boardWidth / 3 + 40, (boardHeight / 3) + 40, 2 * (boardWidth / 3) - 40, 2 * (boardHeight / 3) - 40);
                    }
                } else if (mX > 2 * (boardWidth / 3)) {
                    if (mY < halfHeight - 150 && arrayOfMoves[2] != 1) {
                        makeMove(RIGHT_TOP, 2 * (boardWidth / 3) + 40, 40, boardWidth - 40, boardHeight / 3 - 40);
                        arrayOfMoves[2] = 1;
                    } else if (mY > 2 * (boardHeight / 3) && arrayOfMoves[8] != 1) {
                        makeMove(RIGHT_BOTTOM, 2 * (boardWidth / 3) + 40, 2 * (boardHeight / 3) + 40, boardWidth - 40, boardHeight - 40);
                        arrayOfMoves[8] = 1;
                    } else if (mY < 2 * (boardHeight / 3) && arrayOfMoves[5] != 1) {
                        arrayOfMoves[5] = 1;
                        makeMove(RIGHT_MIDDLE, 2 * (boardWidth / 3) + 40, (boardHeight / 3) + 40, boardWidth - 40, 2 * (boardHeight / 3) - 40);
                    }
                }
                break;
        }
        return true;
    }


    void makeMove(int blockNumber, float startX, float startY, float endX, float endY) {
        Canvas canvas = getHolder().lockCanvas();
        if (isCrossSelected) {
            isGameWon = cross.addMoveBox(blockNumber, startX, startY, endX, endY);
        } else {
            isGameWon = circle.addMoveBox(blockNumber, startX, startY, endX, endY);
        }
        if (isGameWon != -1) {
            String playerName = isCrossSelected ? "Player 2" : " Player 1";
            Toast.makeText(context, "Game won by " + playerName, Toast.LENGTH_LONG).show();
            getWinningLinePointFromResult(isGameWon);
        }
        isCrossSelected = !isCrossSelected;
        getHolder().unlockCanvasAndPost(canvas);
        invalidate();
    }

    private void getWinningLinePointFromResult(int point) {
        switch (point) {
            case ValidateMove.HORIZONTAL_FIRST_LINE:
                winningLinePoint = new Point(40, (boardHeight / 3) / 2, boardWidth - 40, (boardHeight / 3) / 2, -1);
                break;
            case ValidateMove.HORIZONTAL_SECOND_LINE:
                winningLinePoint = new Point(40, (boardHeight / 3) + ((boardHeight) / 3) / 2, boardWidth - 40, (boardHeight / 3) + ((boardHeight) / 3) / 2, -1);
                break;
            case ValidateMove.HORIZONTAL_THIRD_LINE:
                winningLinePoint = new Point(40, 2 * (boardHeight / 3) + ((boardHeight) / 3) / 2, boardWidth - 40, 2 * (boardHeight / 3) + ((boardHeight) / 3) / 2, -1);
                break;
            case ValidateMove.VERTICAL_FIRST_LINE:
                winningLinePoint = new Point((boardWidth / 3) / 2, 40, (boardWidth / 3) / 2, boardHeight - 40, -1);
                break;
            case ValidateMove.VERTICAL_SECOND_LINE:
                winningLinePoint = new Point((boardWidth / 3) + (boardWidth / 3) / 2, 40, (boardWidth / 3) + (boardWidth / 3) / 2, boardHeight - 40, -1);
                break;
            case ValidateMove.VERTICAL_THIRD_LINE:
                winningLinePoint = new Point(2 * (boardWidth / 3) + (boardWidth / 3) / 2, 40, 2 * (boardWidth / 3) + (boardWidth / 3) / 2, boardHeight - 40, -1);
                break;
            case ValidateMove.DIAGONAL_FIRST_LINE:
                winningLinePoint = new Point(40, 40, boardWidth - 40, boardHeight - 40, -1);
                break;
            case ValidateMove.DIAGONAL_SECOND_LINE:
                winningLinePoint = new Point(40, boardHeight - 40, boardWidth - 40, 40, -1);
                break;
        }
    }


    private void initialiseDrawBoard(Canvas canvas) {
        boardPaint = new Paint();
        boardPaint.setColor(Color.BLUE);
        boardPaint.setStrokeWidth(15f);

        crossPaint = new Paint();
        crossPaint.setColor(Color.YELLOW);
        crossPaint.setStrokeWidth(15f);

        circlePaint = new Paint();
        circlePaint.setColor(Color.GREEN);
        circlePaint.setStrokeWidth(15f);
        circlePaint.setStyle(Paint.Style.STROKE);

        winningLinePaint = new Paint();
        winningLinePaint.setColor(Color.RED);
        winningLinePaint.setStrokeWidth(20f);
        winningLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);


        extremeLeft = halfWidth - 400;
        float horizontalLeft = boardWidth / 3;
        float verticalLeft = boardHeight / 3;
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            canvas.drawLine(horizontalLeft, 0, horizontalLeft, 3 * verticalLeft, boardPaint);
            canvas.drawLine(2 * horizontalLeft, 0, 2 * horizontalLeft, 3 * verticalLeft, boardPaint);
            canvas.drawLine(0, verticalLeft, 3 * horizontalLeft, verticalLeft, boardPaint);
            canvas.drawLine(0, 2 * verticalLeft, 3 * horizontalLeft, 2 * verticalLeft, boardPaint);
        }
    }

    public void resetGame() {
        circle.clearMoves();
        cross.clearMoves();
        invalidate();
        arrayOfMoves = new int[]{EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL};
        isGameWon = -1;
        isCrossSelected = false;
    }


}


