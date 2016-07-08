package example.tictactoe.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import example.tictactoe.R;
import example.tictactoe.models.Circle;
import example.tictactoe.models.Cross;
import example.tictactoe.models.Point;

/**
 * Created by hardik on 18/06/16.
 */
public class CanvasBoard extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    //CanvasThread canvasThread;
    int boardWidth, boardHeight;
    Paint boardPaint, crossPaint , circlePaint;
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
    boolean isGameWon;
    int extremeLeft;

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
            float centerX = (cellNumber.getStartX() + cellNumber.getEndX())/2;
            float centerY = (cellNumber.getEndY() + cellNumber.getStartY())/2;
            canvas.drawCircle(centerX,centerY,100f,circlePaint);
        }

        for( Point cellNumber : cross.getMoves()){
            canvas.drawLine(cellNumber.getStartX(), cellNumber.getStartY(), cellNumber.getEndX(), cellNumber.getEndY(), crossPaint);
            canvas.drawLine(cellNumber.getEndX(), cellNumber.getStartY(), cellNumber.getStartX(), cellNumber.getEndY(), crossPaint);
        }
        //canvas.drawColor(Color.WHITE);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Getting the X-Coordinate of the touched position
                mX = event.getX();

                // Getting the Y-Coordinate of the touched position
                mY = event.getY();

                Log.d("#### mWidth " + halfWidth, "halfWidth " + halfHeight);
                if (mX < halfWidth - 150) { //Top left
                    if (mY < halfHeight - 150 ) {
                        makeMove(LEFT_TOP, halfWidth - 380, halfHeight - 350, halfWidth - 180, halfHeight - 180);
                        arrayOfMoves[0] = 1;
                    } else if (mY > halfHeight + 150) {
                        arrayOfMoves[6] = 1;
                        makeMove(LEFT_BOTTOM, halfWidth - 380, halfHeight + 350, halfWidth - 180, halfHeight + 180);
                    } else if (mY < halfHeight + 300) { // Middle
                        arrayOfMoves[3] = 1;
                        makeMove(LEFT_MIDDLE, halfWidth - 380, halfHeight - 80, halfWidth - 180, halfHeight + 80);
                    }
                } else if (mX < halfWidth + 150) {
                    if (mY < halfHeight - 150) {
                        makeMove(CENTER_TOP, halfWidth - 380 + 280, halfHeight - 350, halfWidth - 180 + 280, halfHeight - 180);
                        arrayOfMoves[1] = 1;
                    } else if (mY > halfHeight + 150) {
                        arrayOfMoves[7] = 1;
                        makeMove(CENTER_BOTTOM, halfWidth - 380 + 280, halfHeight + 350, halfWidth - 180 + 280, halfHeight + 180);
                    } else if (mY < halfHeight + 300) {
                        arrayOfMoves[4] = 1;
                        makeMove(CENTER_MIDDLE, halfWidth - 380 + 280, halfHeight - 80, halfWidth - 180 + 280, halfHeight + 80);
                    }
                } else if (mX > halfWidth + 150) {
                    if (mY < halfHeight - 150) {
                        makeMove(RIGHT_TOP, halfWidth + 180, halfHeight - 350, halfWidth + 380, halfHeight - 180);
                        arrayOfMoves[2] = 1;
                    } else if (mY > halfHeight + 150) {
                        makeMove(RIGHT_BOTTOM,  halfWidth + 180, halfHeight + 180 , halfWidth + 380, halfHeight + 350);
                        arrayOfMoves[8] = 1;
                    } else if (mY < halfHeight + 300) {
                        arrayOfMoves[5] = 1;
                        makeMove(RIGHT_MIDDLE, halfWidth + 180, halfHeight - 80, halfWidth + 380, halfHeight + 80);
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
        if(isGameWon){
            Toast.makeText(context,"Game won ",Toast.LENGTH_LONG).show();
        }
        isCrossSelected = !isCrossSelected;
        getHolder().unlockCanvasAndPost(canvas);
        invalidate();
    }

    /*class CanvasThread extends Thread {
        SurfaceHolder surfaceHolder;
        CanvasBoard canvasBoard;
        boolean isRunning;
        boolean newMoveMade;
        float x1, x2, y1, y2;
        int blockNumber;

        public CanvasThread(SurfaceHolder surfaceHolder, CanvasBoard canvasBoard) {
            this.surfaceHolder = surfaceHolder;
            this.canvasBoard = canvasBoard;
        }

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

        void makeMove(int blockNumber) {
            String moveType = isCrossSelected ? "Cross" : "Circle";
            switch (blockNumber) {
                case LEFT_TOP:
                    if (isCrossSelected) {
                        //cross.addMoveBox();
                    } else {

                    }
                    Toast.makeText(context, "Left Top : Draw " + moveType, Toast.LENGTH_LONG).show();
                    break;
                case LEFT_MIDDLE:
                    Toast.makeText(context, "Left Middle : Draw " + moveType, Toast.LENGTH_LONG).show();
                    break;
                case LEFT_BOTTOM:
                    Toast.makeText(context, "Left Bottom : Draw " + moveType, Toast.LENGTH_LONG).show();
                    break;
                case CENTER_TOP:
                    Toast.makeText(context, "Center Top : Draw " + moveType, Toast.LENGTH_LONG).show();
                    break;
                case CENTER_MIDDLE:
                    Toast.makeText(context, "Center Middle : Draw " + moveType, Toast.LENGTH_LONG).show();
                    break;
                case CENTER_BOTTOM:
                    Toast.makeText(context, "Center Bottom : Draw " + moveType, Toast.LENGTH_LONG).show();
                    break;
                case RIGHT_TOP:
                    Toast.makeText(context, "Right Top : Draw " + moveType, Toast.LENGTH_LONG).show();
                    break;
                case RIGHT_MIDDLE:
                    Toast.makeText(context, "Right Middle : Draw " + moveType, Toast.LENGTH_LONG).show();
                    break;
                case RIGHT_BOTTOM:
                    Toast.makeText(context, "Right Bottom : Draw " + moveType, Toast.LENGTH_LONG).show();
                    break;

            }
            isCrossSelected = !isCrossSelected;
            newMoveMade = true;

        }

        @Override
        public void run() {
            super.run();
            while (isRunning) {
                Canvas canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    initialiseDrawBoard(canvas);
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
                postInvalidate();
            }
        }
    }*/

    private void initialiseDrawBoard(Canvas canvas) {
        boardPaint = new Paint();
        boardPaint.setColor(Color.BLUE);
        boardPaint.setStrokeWidth(15f);

        crossPaint = new Paint();
        crossPaint.setColor(Color.BLACK);
        crossPaint.setStrokeWidth(10f);

        circlePaint = new Paint();
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStrokeWidth(10f);
        circlePaint.setStyle(Paint.Style.STROKE);


        extremeLeft = halfWidth - 400;

        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            //Left Vertical line
            canvas.drawLine(halfWidth - 150, halfHeight - 400, halfWidth - 150, halfHeight + 400, boardPaint);
            //Right Vertical line
            canvas.drawLine(halfWidth + 150, halfHeight - 400, halfWidth + 150, halfHeight + 400, boardPaint);
            //Upper horizontal line
            canvas.drawLine(halfWidth - 400, halfHeight - 150, halfWidth + 400, halfHeight - 150, boardPaint);
            //Lower horizontal line
            canvas.drawLine(halfWidth - 400, halfHeight + 150, halfWidth + 400, halfHeight + 150, boardPaint);
        }
    }

}


