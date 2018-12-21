package android.pronixits.com.mapadviceapplication;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class ForgotPasswordActivity extends AppCompatActivity {


    MaterialEditText forgotmail;
    Button submit;

    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Toolbar toolbar = findViewById(R.id.toolbar_forgotpassword);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Map Advice");

        forgotmail = (MaterialEditText)findViewById(R.id.forgotemail);
        submit = (Button)findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String forgotemailtext = forgotmail.getText().toString().trim();

                if(TextUtils.isEmpty(forgotemailtext))
                {
                    Toast.makeText(ForgotPasswordActivity.this, "Email field is Required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressDialog = new ProgressDialog(ForgotPasswordActivity.this);
                    progressDialog.setMessage("Verifying..");
                    progressDialog.show();

                    sendEmail(forgotemailtext);

                }
            }
        });
        
    }

    private void sendEmail(String forgotemailtext) {
        mAuth = FirebaseAuth.getInstance();

        mAuth.sendPasswordResetEmail(forgotemailtext)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(ForgotPasswordActivity.this, "Email sent to Registered Mail-ID", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                            startActivity(intent1);
                            finish();
                        }
                    }
                });

    }
}
