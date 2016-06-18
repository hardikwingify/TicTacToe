package example.tictactoe.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import example.tictactoe.models.Circle;
import example.tictactoe.models.Cross;

/**
 * Created by hardik on 18/06/16.
 */
public class CanvasBoard extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    CanvasThread canvasThread;
    int boardWidth, boardHeight;
    Paint linePaint;
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

    public CanvasBoard(Context context, int boardWidth, int boardHeight) {
        super(context);
        this.context = context;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        halfWidth = boardWidth / 2;
        halfHeight = boardHeight / 2;
        getHolder().addCallback(this);
        arrayOfMoves = new int[9];
        cross = new Cross();
        setOnTouchListener(this);
    }

    public CanvasBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setWillNotDraw(false); // I will draw on my own
        canvasThread = new CanvasThread(holder, this);
        canvasThread.setRunning(true);
        canvasThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        canvasThread.setRunning(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
                    if (mY < halfHeight - 150 && arrayOfMoves[0] != 1) {
                        canvasThread.makeMove(LEFT_TOP);
                        arrayOfMoves[0] = 1;
                    } else if (mY > halfHeight + 150) {
                        arrayOfMoves[6] = 1;
                    } else if (mY < halfHeight + 300) {
                        arrayOfMoves[3] = 1;
                    }
                } else if (mX < halfWidth + 150) {
                    if (mY < halfHeight - 150) {
                        arrayOfMoves[1] = 1;
                    } else if (mY > halfHeight + 150) {
                        arrayOfMoves[7] = 1;
                    } else if (mY < halfHeight + 300) {
                        arrayOfMoves[4] = 1;
                    }
                } else if (mX > halfWidth + 150) {
                    if (mY < halfHeight - 150) {
                        arrayOfMoves[2] = 1;
                    } else if (mY > halfHeight + 150) {
                        arrayOfMoves[8] = 1;
                    } else if (mY < halfHeight + 300) {
                        arrayOfMoves[5] = 1;
                    }
                }
                break;
        }
        return true;
    }


    class CanvasThread extends Thread {
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
            String moveType = isCrossSelected ? "Cross"  :  "Circle";
            switch (blockNumber) {
                case LEFT_TOP:
                    if(isCrossSelected){
                        cross.addMoveBox(0);
                    }else{

                    }
                    Toast.makeText(context, "Left Top : Draw " + moveType , Toast.LENGTH_LONG).show();
                    break;
                case LEFT_MIDDLE:
                    Toast.makeText(context, "Left Middle : Draw " + moveType , Toast.LENGTH_LONG).show();
                    break;
                case LEFT_BOTTOM:
                    Toast.makeText(context, "Left Bottom : Draw " + moveType , Toast.LENGTH_LONG).show();
                    break;
                case CENTER_TOP:
                    Toast.makeText(context, "Center Top : Draw " + moveType , Toast.LENGTH_LONG).show();
                    break;
                case CENTER_MIDDLE:
                    Toast.makeText(context, "Center Middle : Draw " + moveType , Toast.LENGTH_LONG).show();
                    break;
                case CENTER_BOTTOM:
                    Toast.makeText(context, "Center Bottom : Draw " + moveType , Toast.LENGTH_LONG).show();
                    break;
                case RIGHT_TOP:
                    Toast.makeText(context, "Right Top : Draw " + moveType , Toast.LENGTH_LONG).show();
                    break;
                case RIGHT_MIDDLE:
                    Toast.makeText(context, "Right Middle : Draw " + moveType , Toast.LENGTH_LONG).show();
                    break;
                case RIGHT_BOTTOM:
                    Toast.makeText(context, "Right Bottom : Draw " + moveType , Toast.LENGTH_LONG).show();
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
    }

    private void initialiseDrawBoard(Canvas canvas) {
        linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        linePaint.setStrokeWidth(15f);

        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            canvas.drawLine(halfWidth - 150, halfHeight - 400, halfWidth - 150, halfHeight + 400, linePaint);
            canvas.drawLine(halfWidth + 150, halfHeight - 400, halfWidth + 150, halfHeight + 400, linePaint);
            canvas.drawLine(halfWidth - 400, halfHeight - 150, halfWidth + 400, halfHeight - 150, linePaint);
            canvas.drawLine(halfWidth - 400, halfHeight + 150, halfWidth + 400, halfHeight + 150, linePaint);
        }
    }

}


