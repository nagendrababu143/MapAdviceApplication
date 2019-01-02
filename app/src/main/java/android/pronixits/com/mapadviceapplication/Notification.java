package android.pronixits.com.mapadviceapplication;

import android.content.Intent;
import android.pronixits.com.mapadviceapplication.adapter.TrafficPlacesAdapter;
import android.pronixits.com.mapadviceapplication.models.TrafficUpdate;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {

    List<TrafficUpdate> trafficUpdateList = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Toolbar toolbar = (Toolbar) findViewById(R.id.notification_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Traffic Updates");

        listView = (ListView)findViewById(R.id.l_view_traffic);
        onOpenNotificationFun();
    }

    private void onOpenNotificationFun() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Traffic");

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                trafficUpdateList.clear();
                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren())
                {
                    TrafficUpdate trafficUpdate = dataSnapshot2.getValue(TrafficUpdate.class);

                    trafficUpdate.getTraffic_reason();
                    trafficUpdate.getPlacename();
                    trafficUpdate.getLatitude();
                    trafficUpdate.getLongitude();
                    trafficUpdate.getDate();
                    trafficUpdate.getUserid();

                    trafficUpdateList.add(trafficUpdate);

                }

                TrafficPlacesAdapter trafficPlacesAdapter = new TrafficPlacesAdapter(getApplicationContext(),trafficUpdateList);
                listView.setAdapter(trafficPlacesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                    Intent intent = new Intent(Notification.this,MapsActivity.class);
                    startActivity(intent);
                    finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notifymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.trafficupdate)
        {
            startActivity(new Intent(Notification.this,NewNotificationActivity.class));
            return true;
        }
        return false;
    }
}
