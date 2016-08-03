package example.tictactoe;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import example.tictactoe.models.Move;

/**
 * Created by 638 on 7/16/2016.
 */
public class CloudOperations {
    private static final String TAG = "CloudOperations";
    private FirebaseDatabase database;
    DatabaseReference myRef;

    /**
     * Makes cloud connection
     * Should be called once in complete gaming session
     */
    public void connect(String sessionId){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(sessionId);
    }

    /**
     * Send player move to cloud
     * Save server move parameter in Move object and call this method
     * @param move
     */
    public void sendPlayerStatus(Move move){

        myRef.setValue(move);

    }

    /**
     * This method is called whenever opponent makes a move
     */
    public void readOpponentMove(){
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}
