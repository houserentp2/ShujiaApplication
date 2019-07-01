package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import example.com.shujiaapplication.R;

public class ShowBuildingView extends AppCompatActivity {
    private List<CommentsData> commentslist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_building_view);
        initComments();
        CommentAdapter adapter=new CommentAdapter(ShowBuildingView.this,R.layout.commen_item,commentslist);
        ListView listview=(ListView)findViewById(R.id.building_comments);
        listview.setAdapter(adapter);
        Button button1=(Button)findViewById(R.id.return_back);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowBuildingView.this.finish();
            }
        });
    }
    public void initComments(){
        Intent intent=getIntent();
        List<CommentsData> comments = intent.getParcelableArrayListExtra("getComments");
    }
}
