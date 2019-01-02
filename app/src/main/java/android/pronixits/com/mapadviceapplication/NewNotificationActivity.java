package android.pronixits.com.mapadviceapplication;

import android.app.ProgressDialog;
import android.pronixits.com.mapadviceapplication.models.TrafficUpdate;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;
import java.util.Date;

public class NewNotificationActivity extends AppCompatActivity {

    PlaceAutocompleteFragment trafficplacefragement;
    MaterialEditText materialEditText;
    Button submit;
    LatLng trafficplace_latlang;
    FirebaseUser firebaseUser;
    String userid;
    String username;
    //DatabaseReference myRef;
    String trafficreason;
    ProgressDialog progressDialog;
    String placename;
    Double latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notification);

        Toolbar toolbar = (Toolbar) findViewById(R.id.new_notification_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Traffic place");

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        userid = firebaseUser.getUid();


        //myRef = FirebaseDatabase.getInstance().getReference("Traffic");

        materialEditText = (MaterialEditText)findViewById(R.id.traffic_reason_edittext);

        submit = (Button)findViewById(R.id.traffic_place_btn);
        trafficplacefragement = (PlaceAutocompleteFragment)getFragmentManager().findFragmentById(R.id.trafficplacefragment);

        trafficplacefragement.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                trafficplace_latlang = place.getLatLng();
                latitude =trafficplace_latlang.latitude;
                longitude =trafficplace_latlang.longitude;

                placename = place.getName().toString();
            }

            @Override
            public void onError(Status status) {
                Toast.makeText(NewNotificationActivity.this, status.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(NewNotificationActivity.this);
                progressDialog.setMessage("Submitting wait...");
                progressDialog.show();

                trafficreason = materialEditText.getText().toString().trim();



                if (trafficreason != null && trafficplace_latlang != null)
                {
                    saveTrafficFun(trafficreason,latitude,longitude,placename);
                }
                else
                {
                    Toast.makeText(NewNotificationActivity.this, "Traffic Reason and Place is Required...", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private void saveTrafficFun(String trafficreason, Double latitude, Double longitude, String placename) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Traffic");

        Date mydate = Calendar.getInstance().getTime();

        TrafficUpdate trafficUpdate = new TrafficUpdate();

        trafficUpdate.setDate(mydate.toString());
        trafficUpdate.setUserid(userid);
        trafficUpdate.setLatitude(latitude);
        trafficUpdate.setLatitude(longitude);
        trafficUpdate.setTraffic_reason(trafficreason);
        trafficUpdate.setPlacename(placename);


        databaseReference.push().setValue(trafficUpdate).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Toast.makeText(NewNotificationActivity.this, "Successfully Submitted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(NewNotificationActivity.this, "Failed.Please Try Again..!", Toast.LENGTH_SHORT).show();
                }
            }
        });
       /* if(databaseReference.push().setValue(trafficUpdate).isSuccessful())
        {
            Toast.makeText(this, "Successfully Submitted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Failed.Please Try Again..!", Toast.LENGTH_SHORT).show();
        }*/


    }
}
