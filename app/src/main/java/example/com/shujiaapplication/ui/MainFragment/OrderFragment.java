package example.com.shujiaapplication.ui.MainFragment;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import example.com.shujiaapplication.R;
import example.com.shujiaapplication.ui.AuthInfo;
import example.com.shujiaapplication.ui.BuildingListData;
import example.com.shujiaapplication.ui.GetHouseInfo;
import example.com.shujiaapplication.ui.NewBuilding;
import example.com.shujiaapplication.ui.RequsetData;

public class OrderFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private View mview;
    private List<NewBuilding> buildingList=new ArrayList<>();
    private List<BuildingListData>buildingList2=new ArrayList<>();
    private String houseid;
    private BuildingListData buildinglistdata;
    private static  final int GETRENTHOUSELIST = 1;
    private static  final int GETHOUSELIST = 0;
    private static String responseData = "";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try {
                if (msg.what == GETHOUSELIST) {
                    SharedPreferences preferences = getActivity().getSharedPreferences("requestData", getActivity().MODE_PRIVATE);
                    responseData = preferences.getString("requestGetData", "");
                    ArrayList<BuildingListData> buildings = new ArrayList<BuildingListData>();
                    Gson gson = new Gson();
                    buildings = gson.fromJson(responseData, new TypeToken<List<BuildingListData>>() {
                    }.getType());
                    BuildingListData building = new BuildingListData();
                    for (BuildingListData build : buildings) {
                        if (build.getHouseid().equals(houseid)) {
                            building = build;
                            buildinglistdata = building;
                            buildingList2.add(building);
                        }
                    }
                } else if (msg.what == GETRENTHOUSELIST) {
                    SharedPreferences preferences = getActivity().getSharedPreferences("requestData", getActivity().MODE_PRIVATE);
                    responseData = preferences.getString("requestGetData", "");
                    Gson gson = new Gson();
                    buildingList = gson.fromJson(responseData, new TypeToken<List<NewBuilding>>() {
                    }.getType());
                    Log.e("OrderFragment","newbuildingnumber_________________"+buildingList.size());
                    OrderFragmentS1 a=new OrderFragmentS1();
                    AuthInfo.setBuildingList(buildingList);
                    AuthInfo.setBuildingList2(buildingList2);
                    Log.e("auth","newbuildingnumber_________________"+AuthInfo.getBuildingList().size());
                    replaceFragment(a);
                }
            }catch(Exception e){
                Toast.makeText(getActivity(),"目前无房屋",Toast.LENGTH_SHORT);
            }
        }
    };


    private OnFragmentInteractionListener mListener;

    public OrderFragment() {
        // Required empty public constructor
    }
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBuildings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_order, container, false);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=this.getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.order,fragment);
        transaction.commit();
    }
    public void initBuildings() {
        getRentBuildingInformation();
        for(NewBuilding building:buildingList){
            houseid=building.getHouseid();
            getBuildingInformation(building.getUserid(),building.getToken(),building.getHouseid());
        }

    }
    public void getRentBuildingInformation(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetHouseInfo a=new GetHouseInfo(AuthInfo.userid,AuthInfo.token);
                RequsetData.requestData(a,"getmyrented");
                Message message = new Message();
                message.what = GETRENTHOUSELIST;
                handler.sendMessage(message);
            }
        }).start();
    }
    public void getBuildingInformation(String Userid, String Token,String Houseid){
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetHouseInfo a=new GetHouseInfo(Userid,Token);
                RequsetData.requestData(a,"gethouselist");
                Message message = new Message();
                message.what = GETHOUSELIST;
                handler.sendMessage(message);
            }
        }).start();
    }
}
