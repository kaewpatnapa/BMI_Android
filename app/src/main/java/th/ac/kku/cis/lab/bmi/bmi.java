package th.ac.kku.cis.lab.bmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;

public class bmi extends AppCompatActivity {
    EditText input_w;
    EditText input_h;
    Button btn_cal;
    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("BMI").push();

        final DecimalFormat Dformat = new DecimalFormat("##.##");

        input_w = (EditText) findViewById(R.id.editText3_w);
        input_h = (EditText) findViewById(R.id.editText4_h);
        btn_cal = (Button) findViewById(R.id.Button_cal);
        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double wi = Double.valueOf(input_w.getText().toString());
                double hi = Double.valueOf(input_h.getText().toString());
                double bmi =  wi/(Math.pow((hi/100),2));
                mData.child("Heigh").setValue(String.valueOf(hi));
                mData.child("Weight").setValue(String.valueOf(wi));
                mData.child("BMI").setValue(String.valueOf(Dformat.format(bmi)));
                mData.child("userid").setValue(user.getUid());
                Intent intent = new Intent(bmi.this,detail.class);
                intent.putExtra("BMI",String.valueOf(Dformat.format(bmi)));
                startActivity(intent);
                finish();
            }
        });
    }
}
