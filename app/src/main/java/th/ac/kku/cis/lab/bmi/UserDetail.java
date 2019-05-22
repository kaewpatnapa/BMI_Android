package th.ac.kku.cis.lab.bmi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDetail extends AppCompatActivity {
    String postkey;
    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;
    TextView name_us;
    TextView phone_us;
    TextView email_us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        postkey = getIntent().getExtras().getString("PostKey");

        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("User").child(postkey);

        name_us = (TextView) findViewById(R.id.Name_user);
        phone_us = (TextView) findViewById(R.id.phone_user);
        email_us = (TextView) findViewById(R.id.email_user);

        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              String name = dataSnapshot.child("name").getValue().toString();
              String phone = dataSnapshot.child("phone").getValue().toString();
              String email = dataSnapshot.child("email").getValue().toString();

              name_us.setText(name);
              phone_us.setText(phone);
              email_us.setText(email);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
