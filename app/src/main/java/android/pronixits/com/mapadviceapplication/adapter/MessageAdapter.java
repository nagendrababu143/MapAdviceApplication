package android.pronixits.com.mapadviceapplication.adapter;

import android.content.Context;
import android.pronixits.com.mapadviceapplication.R;
import android.pronixits.com.mapadviceapplication.models.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.zip.Inflater;


public class MessageAdapter extends BaseAdapter {


    private Context context;
    private List<Message> messages;
    FirebaseUser user;

    /*public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;*/

    String userid = "TZLMGL79u3WDbE1EAsFpui7PUPW2";


    public MessageAdapter() {
    }

    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }


    @Override
    public int getCount() {
        return messages.size();
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

        View view1;
        int type = getItemViewType(i);

       /* View view1 = View.inflate(context, R.layout.chat_item_left, null);
        return view1;*/

       if (type == 1)
       {
           view1 = View.inflate(context, R.layout.chat_item_right, null);
           TextView textView = (TextView)view1.findViewById(R.id.show_message_right);

           Message message = messages.get(i);
           textView.setText(message.getMessage());
       }
       else
       {
           view1 = View.inflate(context, R.layout.chat_item_left, null);
           TextView textView1 = (TextView)view1.findViewById(R.id.lefttext);

           Message message = messages.get(i);
           textView1.setText(message.getMessage());


       }

       return view1;
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        user = FirebaseAuth.getInstance().getCurrentUser();

        if(messages.get(position).getSender().equals(user.getUid()))
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

}
