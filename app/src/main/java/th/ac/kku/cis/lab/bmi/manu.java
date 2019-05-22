package th.ac.kku.cis.lab.bmi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class manu extends AppCompatActivity {
    FirebaseAuth mAuth;
    ImageButton btnBmit;
    ImageButton btnBurn;
    ImageButton btnActivity;
    ImageButton imageUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mData;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        btnBmit = (ImageButton) findViewById(R.id.image1);
        btnBurn = (ImageButton) findViewById(R.id.image2);
        btnActivity = (ImageButton) findViewById(R.id.image3);
        imageUser = (ImageButton) findViewById(R.id.image4);
        imageUser.setVisibility(View.INVISIBLE);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("User").child(user.getUid());
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datauser shap = dataSnapshot.getValue(datauser.class);
                String userSta = shap.getName();
                
                if(userSta.equals("admin")){
                    imageUser.setVisibility(View.VISIBLE);
                }
                else if(userSta.equals("people")){
                    imageUser.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        imageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(manu.this,User_Page.class);
                startActivity(intent);
            }
        });

        btnBmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bmi = new Intent(manu.this,bmi.class);
                startActivity(bmi);
            }
        });

        btnBurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent burn = new Intent(manu.this,action.class);
                startActivity(burn);
            }
        });

        btnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(manu.this,save.class);
                startActivity(activity);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                mAuth.signOut();
                Intent logoutIn = new Intent(manu.this,admin.class);
                startActivity(logoutIn);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
