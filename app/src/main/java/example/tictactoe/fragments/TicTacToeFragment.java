package example.tictactoe.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.tictactoe.Utils.Utils;
import example.tictactoe.view.CanvasBoard;

/**
 * Created by hardik on 08/07/16.
 */
public class TicTacToeFragment extends Fragment {
    CanvasBoard canvasBoard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        canvasBoard = new CanvasBoard(getContext(), Utils.getDeviceWidth(getActivity()), Utils.getDeviceHeight(getActivity()));
        return canvasBoard;
    }

    public void resetGame(){
        canvasBoard.resetGame();
    }
}
