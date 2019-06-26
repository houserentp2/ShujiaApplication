package example.com.shujiaapplication.ui.MainFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import example.com.shujiaapplication.R;
import example.com.shujiaapplication.ui.ActivityCollector;
import example.com.shujiaapplication.ui.DiscountActivity;
import example.com.shujiaapplication.ui.IntroduceAppActivity;
import example.com.shujiaapplication.ui.LandlordActivity;
import example.com.shujiaapplication.ui.MainActivity;
import example.com.shujiaapplication.ui.ManageTravelerActivity;
import example.com.shujiaapplication.ui.MyApplication;
import example.com.shujiaapplication.ui.PersonalInfoActivity;
import example.com.shujiaapplication.ui.ScoreActivity;
import example.com.shujiaapplication.ui.ShowBuildListActivity;

public class MyFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private GridView gridView;
    private Button exitAccount;

    private OnFragmentInteractionListener mListener;

    public MyFragment() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        AppCompatActivity appCompatActivity= (AppCompatActivity) getActivity();
        Toolbar toolbar= (Toolbar) appCompatActivity.findViewById(R.id.myToolbar);
        appCompatActivity.setSupportActionBar(toolbar);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.my_toolbar,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.setPassward:{
                Toast.makeText(MyApplication.getContext(),"你点击了修改密码",Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return true;
    }

    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,container,false);
        List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        String[] name = new String[]{"优惠券","个人信息","房屋管理","我的评论","联系客服","积分","常用入住人","软件介绍","备用按钮"};
        for(int i=0;i<9;i++){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("imageItem", R.drawable.tab_collect_selector);//添加图像资源的ID
            item.put("textItem", name[i]);//按序号添加ItemText
            items.add(item);
        }

        exitAccount = (Button)view.findViewById(R.id.exitAccount);
        exitAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
                Intent intent1 = new Intent(MyApplication.getContext(), MainActivity.class);
                MyApplication.getContext().startActivity(intent1);
            }
        });
        SimpleAdapter adapter = new SimpleAdapter(getContext(),items,R.layout.my_grid_item,new String[]{"imageItem", "textItem"},new int[]{R.id.image_item, R.id.text_item});
        gridView = (GridView)view.findViewById(R.id.my_gridView);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:{
                Intent intent = new Intent(MyApplication.getContext(), DiscountActivity.class);
                startActivity(intent);
                break;
            }
            case 1:{
                Intent intent = new Intent(MyApplication.getContext(),PersonalInfoActivity.class);
                startActivity(intent);
                break;
            }
            case 2:{
                Intent intent = new Intent(MyApplication.getContext(), LandlordActivity.class);
                startActivity(intent);
                break;
            }
            case 3:{
                Toast.makeText(MyApplication.getContext(),"我的评论", Toast.LENGTH_SHORT).show();
                break;
            }
            case 4:{
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
                break;
            }
            case 5:{
                Intent intent = new Intent(MyApplication.getContext(),ScoreActivity.class);
                startActivity(intent);
                break;
            }
            case 6:{
                Intent intent = new Intent(MyApplication.getContext(),ManageTravelerActivity.class);
                startActivity(intent);
                break;
            }
            case 7:{
                Intent intent = new Intent(MyApplication.getContext(), IntroduceAppActivity.class);
                startActivity(intent);
                break;
            }
            case 8:{
                Intent intent = new Intent(MyApplication.getContext(), ShowBuildListActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
