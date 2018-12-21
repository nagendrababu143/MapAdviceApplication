package android.pronixits.com.mapadviceapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    Button login_btn,register_btn;
    Intent intent;

    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null )
        {
            Intent intent1 = new Intent(StartActivity.this,MapsActivity.class);
            startActivity(intent1);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        login_btn = (Button)findViewById(R.id.startlogin);
        register_btn = (Button)findViewById(R.id.startregister);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
