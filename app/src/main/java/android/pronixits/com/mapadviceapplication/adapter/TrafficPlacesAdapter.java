package android.pronixits.com.mapadviceapplication.adapter;

import android.content.Context;
import android.pronixits.com.mapadviceapplication.R;
import android.pronixits.com.mapadviceapplication.models.Message;
import android.pronixits.com.mapadviceapplication.models.TrafficUpdate;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TrafficPlacesAdapter extends BaseAdapter{

    private Context context;
    private List<TrafficUpdate> trafficUpdateList;

    public TrafficPlacesAdapter() {
    }

    public TrafficPlacesAdapter(Context context, List<TrafficUpdate> trafficUpdateList) {
        this.context = context;
        this.trafficUpdateList = trafficUpdateList;
    }

    @Override
    public int getCount() {
        return trafficUpdateList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View view1 = View.inflate(context, R.layout.traffic_item,null);

        TrafficUpdate trafficUpdate1 = trafficUpdateList.get(i);

        TextView textView = (TextView)view1.findViewById(R.id.reason_name_textview);
        TextView textView1 = (TextView)view1.findViewById(R.id.place_name_textview);
        TextView textView2 = (TextView)view1.findViewById(R.id.time_traffic);

        CardView cardView = (CardView)view1.findViewById(R.id.card);


        textView.setText(trafficUpdate1.getTraffic_reason());
        textView1.setText(trafficUpdate1.getPlacename());
        textView2.setText(trafficUpdate1.getDate());

        /*cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(, "", Toast.LENGTH_SHORT).show();
            }
        });*/


        return view1;
    }
}
