package example.com.shujiaapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
        String[] name = new String[]{"优惠券","个人信息","发布房屋","我的评论","联系客服","积分","常用入住人","软件介绍","备用按钮"};
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
                Intent intent1 = new Intent("example.com.shujiaapplication.FORCE_OFFLINE");
                MyApplication.getContext().sendBroadcast(intent1);
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
                Toast.makeText(MyApplication.getContext(),"优惠券", Toast.LENGTH_SHORT).show();
                break;
            }
            case 1:{
                Intent intent = new Intent(MyApplication.getContext(),PersonalInfoActivity.class);
                startActivity(intent);
            }
            case 2:{
                Toast.makeText(MyApplication.getContext(),"发布房屋", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MyApplication.getContext(),"软件介绍", Toast.LENGTH_SHORT).show();
                break;
            }
            case 8:{
                Toast.makeText(MyApplication.getContext(),"备用按钮", Toast.LENGTH_SHORT).show();
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
