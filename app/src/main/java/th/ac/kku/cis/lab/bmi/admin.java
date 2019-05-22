package th.ac.kku.cis.lab.bmi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class admin extends AppCompatActivity {
    EditText login_input;
    EditText pass_input;
    Button btn_Lo;
    FirebaseAuth mAuth;
    FirebaseUser user;
    Button btn_re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        login_input = (EditText) findViewById(R.id.editText_email);
        pass_input = (EditText) findViewById(R.id.editText2_password);
        btn_Lo = (Button) findViewById(R.id.btn_login);
        btn_re = (Button) findViewById(R.id.btn_register);
        btn_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regis = new Intent(admin.this,RegisterPage.class);
                startActivity(regis);
            }
        });
        mAuth = FirebaseAuth.getInstance();



        btn_Lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_input.getText().toString();
                String pass  = pass_input.getText().toString();
                mAuth.signInWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    user = mAuth.getCurrentUser();
                                    if(user != null) {
                                        Intent intent = new Intent(admin.this, manu.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                                else{
                                    Toast.makeText(admin.this,"Error Login",Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });

    }
}
