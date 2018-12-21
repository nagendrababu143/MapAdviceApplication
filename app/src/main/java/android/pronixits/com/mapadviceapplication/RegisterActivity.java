package android.pronixits.com.mapadviceapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.pronixits.com.mapadviceapplication.models.UserInformation;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class RegisterActivity extends AppCompatActivity {


    MaterialEditText username,email,password;
    Button btn_register;

    FirebaseAuth auth;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar_register);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        username = (MaterialEditText)findViewById(R.id.registerusername);
        email = (MaterialEditText)findViewById(R.id.registeremail);
        password = (MaterialEditText)findViewById(R.id.registerpassword);
        btn_register = (Button)findViewById(R.id.btn_register);



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = username.getText().toString().trim();
                String txt_email = email.getText().toString().trim();
                String txt_password = password.getText().toString().trim();

                if(TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password))
                {
                    Toast.makeText(RegisterActivity.this, "All fields are Required", Toast.LENGTH_SHORT).show();
                }
                else if(txt_password.length() < 6 )
                {
                    Toast.makeText(RegisterActivity.this, "Password Length should be at least 7 characters", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressDialog = new ProgressDialog(RegisterActivity.this);
                    progressDialog.setMessage("Registering wait..");
                    progressDialog.show();

                    register(txt_username,txt_email,txt_password);
                }

            }
        });
    }
    private void register(final String username, final String email, String password) {

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            FirebaseUser firebaseUser = auth.getCurrentUser();

                            assert firebaseUser != null;

                            String userid = firebaseUser.getUid();


                            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users").child(userid);


                            UserInformation userInformation = new UserInformation();

                            userInformation.setUserid(userid);
                            userInformation.setEmail(email);
                            userInformation.setUsername(username);
                            userInformation.setImageurl("default");



                            myRef.setValue(userInformation).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        progressDialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });

                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Registration Failure", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}
