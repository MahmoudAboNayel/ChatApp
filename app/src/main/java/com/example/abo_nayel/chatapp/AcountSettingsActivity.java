package com.example.abo_nayel.chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class AcountSettingsActivity extends AppCompatActivity {

    DatabaseReference database;
    TextView nametxt, statustxt;
    CircleImageView acc_image;
    Button imagebtn, statusbtn;
    private FirebaseUser current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount_settings);
        nametxt=(TextView)findViewById(R.id.acc_name);
        statustxt = (TextView)findViewById(R.id.acc_status);
        statusbtn = (Button)findViewById(R.id.acc_status_btn) ;

        current_user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = current_user.getUid();
        database = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nametxt.setText(dataSnapshot.child("name").getValue().toString());
                statustxt.setText(dataSnapshot.child("status").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        statusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),
                        StatusActivity.class).putExtra("status",statustxt.getText().toString()));

            }
        });

    }
}
