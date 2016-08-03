package example.tictactoe;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import example.tictactoe.Utils.AppPrefs;

/**
 * Created by hardik on 03/08/16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppPrefs.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
