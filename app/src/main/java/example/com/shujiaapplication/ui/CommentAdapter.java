package example.com.shujiaapplication.ui;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.List;
import java.util.concurrent.TimeoutException;

import example.com.shujiaapplication.R;

public class CommentAdapter extends ArrayAdapter<CommentsData> {
    private int resourceId;
    public CommentAdapter(Context context, int textViewResourceId, List<CommentsData> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        CommentsData comment= (CommentsData) getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView icon=(ImageView)view.findViewById(R.id.head);
        TextView comments=(TextView)view.findViewById(R.id.comment);
        comments.setText(comment.getComment());
        return view;

    }
}
