package example.com.shujiaapplication.ui.MainFragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import example.com.shujiaapplication.R;
import example.com.shujiaapplication.ui.AuthInfo;
import example.com.shujiaapplication.ui.DiscountData;
import example.com.shujiaapplication.ui.RequsetData;
import example.com.shujiaapplication.ui.SearchData;
import example.com.shujiaapplication.ui.ShowBuildListActivity;

public class LongFragment extends MainFatherFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private int searchType = 0;
    private TextView cityText;
    private TextView positionText;
    private Button searchButton;
    private Button oneRoom;
    private Button twoRooms;
    private Button threeRooms;
    private Button intifeRooms;
    private static String responseData = "";

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                SharedPreferences preferences = getActivity().getSharedPreferences("requestData", Context.MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                if(!responseData.equals("")){
                    Toast.makeText(getActivity(),"成功!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), ShowBuildListActivity.class);
                    intent.putExtra("getHouseList",responseData);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(),responseData,Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    public LongFragment() {
        // Required empty public constructor
    }

    public static LongFragment newInstance(String param1, String param2) {
        LongFragment fragment = new LongFragment();
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
        return inflater.inflate(R.layout.fragment_long, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView(){
        oneRoom = (Button)getActivity().findViewById(R.id.one_type);
        twoRooms = (Button)getActivity().findViewById(R.id.two_type);
        threeRooms = (Button)getActivity().findViewById(R.id.three_type);
        intifeRooms = (Button)getActivity().findViewById(R.id.all_type);

        cityText = (TextView) getActivity().findViewById(R.id.city_name_long);
        cityText.setOnClickListener(new CityOnClickListener());
        cityText.setText(city);
        positionText = (TextView) getActivity().findViewById(R.id.my_position_long);
        positionText.setOnClickListener(new CityOnClickListener());
        searchButton = (Button) getActivity().findViewById(R.id.search_long_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiscountData discount = new DiscountData(AuthInfo.userid,AuthInfo.token);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RequsetData.requestData(discount,"gethouselist");
                        Message message = new Message();
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

        oneRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneRoom.setBackgroundColor(0xfffff678);
                twoRooms.setBackgroundColor(0xffffff);
                threeRooms.setBackgroundColor(0xffffff);
                intifeRooms.setBackgroundColor(0xffffff);
            }
        });

        twoRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneRoom.setBackgroundColor(0xffffffff);
                twoRooms.setBackgroundColor(0xfffff678);
                threeRooms.setBackgroundColor(0xffffffff);
                intifeRooms.setBackgroundColor(0xffffffff);
            }
        });

        threeRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneRoom.setBackgroundColor(0xffffffff);
                threeRooms.setBackgroundColor(0xfffff678);
                twoRooms.setBackgroundColor(0xffffffff);
                intifeRooms.setBackgroundColor(0xffffffff);
            }
        });

        intifeRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneRoom.setBackgroundColor(0xffffffff);
                threeRooms.setBackgroundColor(0xffffffff);
                twoRooms.setBackgroundColor(0xffffffff);
                intifeRooms.setBackgroundColor(0xfffff678);
            }
        });



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
