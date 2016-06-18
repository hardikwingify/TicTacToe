package example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import example.tictactoe.Utils.Utils;
import example.tictactoe.view.CanvasBoard;

public class MainActivity extends AppCompatActivity {

    CanvasBoard canvasBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canvasBoard = new CanvasBoard(this, Utils.getDeviceWidth(this), Utils.getDeviceHeight(this));
        setContentView(canvasBoard);
    }
}
