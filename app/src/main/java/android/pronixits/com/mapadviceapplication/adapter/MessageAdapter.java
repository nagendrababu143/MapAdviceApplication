package android.pronixits.com.mapadviceapplication.adapter;

import android.content.Context;
import android.pronixits.com.mapadviceapplication.models.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.zip.Inflater;


public class MessageAdapter extends BaseAdapter {


    private Context context;
    private List<Message> messages;
    FirebaseUser user;

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;


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

        return null;
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(messages.get(position).getSender().equals(user.getUid()))
        {
            return MSG_TYPE_RIGHT;
        }
        else
        {
            return MSG_TYPE_LEFT;
        }
    }
}
