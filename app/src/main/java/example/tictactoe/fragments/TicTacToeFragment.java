package example.tictactoe.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import example.tictactoe.Utils.AppPrefs;
import example.tictactoe.Utils.Utils;
import example.tictactoe.auth.UserAuthentication;
import example.tictactoe.view.CanvasBoard;

/**
 * Created by hardik on 08/07/16.
 */
public class TicTacToeFragment extends Fragment {
    CanvasBoard canvasBoard;
    final int USER_AUTHENTICATION = 121;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        if (AppPrefs.getUsername() == null) {
            startActivityForResult(new Intent(container.getContext(), UserAuthentication.class), USER_AUTHENTICATION);
        }
        canvasBoard = new CanvasBoard(getContext(), Utils.getDeviceWidth(getActivity()), Utils.getDeviceHeight(getActivity()));
        return canvasBoard;
    }

    public void resetGame() {
        canvasBoard.resetGame();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == USER_AUTHENTICATION && resultCode != Activity.RESULT_OK) {
            Toast.makeText(context, "You Won't be able to play mutliplayer without signing in", Toast.LENGTH_LONG).show();
        }
    }
}
