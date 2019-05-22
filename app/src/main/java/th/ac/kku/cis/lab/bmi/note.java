package th.ac.kku.cis.lab.bmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class note extends AppCompatActivity {
    EditText activity;
    Button btn_save;
    ImageView next_btn;
    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        activity = (EditText) findViewById(R.id.textView_activity);
        btn_save = (Button) findViewById(R.id.Button_Save);
        next_btn = (ImageView) findViewById(R.id.btn_next_activity);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("Activity").push();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(note.this,savenote.class);
                startActivity(intent);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_Ac = activity.getText().toString();
                mData.child("detail").setValue(input_Ac);
                mData.child("userid").setValue(user.getUid());
                Intent intent = new Intent(note.this,savenote.class);
                startActivity(intent);
                Toast.makeText(note.this,"Success !!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
