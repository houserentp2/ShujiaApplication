package example.com.shujiaapplication.ui.MainFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import example.com.shujiaapplication.ui.AuthInfo;
import example.com.shujiaapplication.ui.CheckerData;
import example.com.shujiaapplication.ui.DiscountActivity;
import example.com.shujiaapplication.ui.DiscountData;
import example.com.shujiaapplication.ui.IntroduceAppActivity;
import example.com.shujiaapplication.ui.LandlordActivity;
import example.com.shujiaapplication.ui.MainActivity;
import example.com.shujiaapplication.ui.ManageTravelerActivity;
import example.com.shujiaapplication.ui.MyApplication;
import example.com.shujiaapplication.ui.PersonalInfoActivity;
import example.com.shujiaapplication.ui.RequsetData;
import example.com.shujiaapplication.ui.ScoreActivity;
import example.com.shujiaapplication.ui.ShowBuildListActivity;
import example.com.shujiaapplication.ui.VerifyActivity;

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

        String[] name = new String[]{"优惠券","个人信息","房屋管理","我的评论","联系客服","积分","常用入住人","软件介绍","审核"};
        int[] images = new int[]{R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5,R.drawable.i6,R.drawable.i7,R.drawable.i8,R.drawable.i9};

        for(int i=0;i<9;i++){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("imageItem",images[i]);//添加图像资源的ID
            item.put("textItem", name[i]);//按序号添加ItemText
            items.add(item);
        }

        exitAccount = (Button)view.findViewById(R.id.exitAccount);
        exitAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("autoLogin", Context.MODE_PRIVATE).edit();
                editor.putString("autoLoginPhone","");
                editor.putString("autoLoginPassword","");
                editor.apply();

                ActivityCollector.finishAll();
                Intent intent1 = new Intent(MyApplication.getContext(), MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
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
                Intent intent = new Intent(MyApplication.getContext(), VerifyActivity.class);
                startActivity(intent);

                CheckerData checker = new CheckerData(AuthInfo.userid,AuthInfo.token,"");
//                String responseStr = RequsetData.requestData(checker,"joinchecker");
//                if (responseStr.contains("Existed")){
//                    Toast.makeText(MyApplication.getContext(),"恭喜你成为审核员", Toast.LENGTH_SHORT).show();
//                    switchVerify();
//                }

                DiscountData discount = new DiscountData(AuthInfo.userid,AuthInfo.token);
//                String responseGet = RequsetData.requestData(discount,"getcheckerinfo");
//                if(responseGet.contains("Existed")){
//                    switchVerify();
//                }
                break;
            }
        }
    }

    private void switchVerify(){
        Intent intent = new Intent(MyApplication.getContext(), VerifyActivity.class);
        startActivity(intent);
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
