package example.tictactoe;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import example.tictactoe.Utils.Utils;
import example.tictactoe.fragments.TicTacToeFragment;
import example.tictactoe.view.CanvasBoard;

public class MainActivity extends AppCompatActivity {

    CanvasBoard canvasBoard;
    TicTacToeFragment gameFragment;
    Button btnClearGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canvasBoard = new CanvasBoard(this, Utils.getDeviceWidth(this), Utils.getDeviceHeight(this));
        setContentView(R.layout.activity_main);
        gameFragment = new TicTacToeFragment();
        btnClearGame = (Button) findViewById(R.id.btn_reset);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame_container, gameFragment);
        transaction.commit();
        btnClearGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameFragment.resetGame();
            }
        });


    }
}
