package net.kaparray.velp.utils;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.kaparray.velp.R;



@SuppressLint("Registered")
public class FirebaseIntegration extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "Login";
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    public FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public String emailUser;
    public  String nameUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nameUser = dataSnapshot.child("Users").child(user.getUid()).child("name").getValue().toString();
                emailUser = dataSnapshot.child("Users").child(user.getUid()).child("email").getValue().toString();

                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                View headerView = navigationView.getHeaderView(0);

                TextView navName = (TextView) headerView.findViewById(R.id.tv_username);
                navName.setText(nameUser);
                TextView navEmail = (TextView) headerView.findViewById(R.id.tv_emainuser);
                navEmail.setText(emailUser);
                Log.w("Connect to db", "Data user add to MainActivity: " + nameUser + " " + emailUser);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabase.addValueEventListener(postListener);



    }

}