package example.tictactoe.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hardik on 03/08/16.
 */
public class AppPrefs {
    private static final String USERNAME = "username";
    private static final String USERID = "userid";
    private static final String USEREMAIL = "userid";
    private static final String PREFS_NAME = "user_keychain";
    private static SharedPreferences sharedPreferences;

    public static String getUseremail() {
        return sharedPreferences.getString(USEREMAIL,null);
    }

    public static void setUseremail(String USEREMAIL) {
        sharedPreferences.edit().putString(USEREMAIL,USEREMAIL).commit();
    }

    public static void init(Context context){
        sharedPreferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
    }

    public static String getUsername(){
        return sharedPreferences.getString(USERNAME,null);
    }

    public static void setUsername(String username){
        sharedPreferences.edit().putString(USERNAME,username).commit();
    }

    public static String getUserId(){
        return sharedPreferences.getString(USERID,null);
    }

    public static void setUserId(String userId){
        sharedPreferences.edit().putString(USERID,userId).commit();
    }


}
