package th.ac.kku.cis.lab.bmi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends AppCompatActivity {
    EditText emailRe;
    EditText nameRe;
    EditText passRe;
    EditText phoneRe;
    EditText repassRe;
    Button btn_Regis;
    FirebaseUser user;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        emailRe = (EditText) findViewById(R.id.email_regis);
        nameRe = (EditText) findViewById(R.id.Name_regis);
        passRe = (EditText) findViewById(R.id.Password_regis);
        repassRe = (EditText) findViewById(R.id.Password_re_regis);
        phoneRe = (EditText) findViewById(R.id.Phone_regis);
        btn_Regis = (Button) findViewById(R.id.btn_register);

        mAuth = FirebaseAuth.getInstance();


        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("User");

        btn_Regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailRe.getText().toString();
                final String name = nameRe.getText().toString();
                String pass = passRe.getText().toString();
                final String phone = phoneRe.getText().toString();
                String re_pass = repassRe.getText().toString();
                if(!pass.equals(re_pass)){
                    Toast.makeText(RegisterPage.this,"Pass don't match!!!",Toast.LENGTH_LONG).show();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                  if(task.isSuccessful()){
                                      user = mAuth.getCurrentUser();
                                      mData.child(user.getUid()).child("email").setValue(email);
                                      mData.child(user.getUid()).child("name").setValue(name);
                                      mData.child(user.getUid()).child("phone").setValue(phone);
                                      mData.child(user.getUid()).child("userid").setValue(user.getUid());
                                      mData.child(user.getUid()).child("status").setValue("people");
                                      Intent manuI = new Intent(RegisterPage.this,manu.class);
                                      startActivity(manuI);
                                      finish();
                                  }
                                  else{
                                      Log.w("Error", "createUserWithEmail:failure", task.getException());
                                      Toast.makeText(RegisterPage.this, "Authentication failed.",
                                              Toast.LENGTH_SHORT).show();
                                  }
                                }
                            });
                }
            }
        });

    }
}
