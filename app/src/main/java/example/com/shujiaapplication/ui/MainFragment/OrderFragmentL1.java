package example.com.shujiaapplication.ui.MainFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import example.com.shujiaapplication.R;
import example.com.shujiaapplication.ui.AuthInfo;
import example.com.shujiaapplication.ui.Building;
import example.com.shujiaapplication.ui.BuildingAdapter;
import example.com.shujiaapplication.ui.BuildingListData;
import example.com.shujiaapplication.ui.GetHouseInfo;
import example.com.shujiaapplication.ui.NewBuilding;
import example.com.shujiaapplication.ui.RequsetData;


public class OrderFragmentL1 extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mview;
    private List<NewBuilding> buildingList=new ArrayList<NewBuilding>();
    private List<NewBuilding> buildingList2=new ArrayList<NewBuilding>();
    private String houseid;
    private BuildingListData buildinglistdata;
    private static  final int GETRENTHOUSELIST = 1;
    private static  final int GETHOUSELIST = 0;
    private static String responseData = "";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==GETHOUSELIST){
                SharedPreferences preferences = getActivity().getSharedPreferences("requestData",getActivity().MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                ArrayList<BuildingListData> buildings = new ArrayList<BuildingListData>();
                Gson gson = new Gson();
                buildings = gson.fromJson(responseData,new TypeToken<List<BuildingListData>>(){}.getType());
                BuildingListData building=new BuildingListData();
                for(BuildingListData build:buildings){
                    if(build.getHouseid().equals(houseid)){
                        building=build;
                        buildinglistdata=building;
                    }
                }
            }else if(msg.what==GETRENTHOUSELIST){
                SharedPreferences preferences = getActivity().getSharedPreferences("requestData",getActivity().MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                Gson gson = new Gson();
                buildingList = gson.fromJson(responseData,new TypeToken<List<NewBuilding>>(){}.getType());
            }
        }
    };
    public OrderFragmentL1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragmentL3.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragmentL2 newInstance(String param1, String param2) {
        OrderFragmentL2 fragment = new OrderFragmentL2();
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
        initBuildings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mview=inflater.inflate(R.layout.fragment_order_fragment_l1, container, false);
        RecyclerView recyclerView=(RecyclerView)mview.findViewById(R.id.view_apply);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        BuildingAdapter adapter=new BuildingAdapter(buildingList2);
        recyclerView.setAdapter(adapter);
        return mview;
    }
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        Button a=mview.findViewById(R.id.button_leftno);
        Button b=mview.findViewById(R.id.button_sign);
        Button c=mview.findViewById(R.id.button_finish);
        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
    }
    public void onClick(View view){
        switch(view.getId()){
            case R.id.button_leftno:
                replaceFragment(new OrderFragmentS1());
                break;
            case R.id.button_sign:
                replaceFragment(new OrderFragmentL2());
                break;
            case R.id.button_finish:
                replaceFragment(new OrderFragmentL3());
                break;
            default:
                break;
        }
    }
    public void initBuildings(){
        getRentBuildingInformation();
        for(NewBuilding building:buildingList){
            houseid=building.getHouseid();
            getBuildingInformation(building.getUserid(),building.getToken(),building.getHouseid());
            BuildingListData b=buildinglistdata;
            String start=building.getStart();
            String stop=building.getStop();
            int getpaied=Integer.valueOf(building.getResult());
            if(b.getOthers().getLongx()==1){
                    if(isLaterToLocalTime(start)==1){
                        buildingList2.add(building);
                    }else{
                        if(isLaterToLocalTime(stop)==1){

                        }else{
                            buildingList2.add(building);
                        }
                    }
            }
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
    //传入时间比当前时间小时，返回0
    public int isLaterToLocalTime(String a){
        Calendar cal=Calendar.getInstance();
        int y=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH)+1;
        int d=cal.get(Calendar.DATE);
        int h=cal.get(Calendar.HOUR_OF_DAY);
        int mi=cal.get(Calendar.MINUTE);
        int s=cal.get(Calendar.SECOND);
        String[] a1=a.split("\\+");
        String[] a2=a1[0].split(":");
        String a3=a2[0]+a2[1]+a2[2];
        String[] a4=a3.split("T");
        String a5=a4[0]+"."+a4[1];
        String[] a6=a5.split("-");
        String a7=a6[0]+a6[1]+a6[2];
        String[] a8=a7.split("\\.");
        int fortime=Integer.valueOf(a8[0]);
        int backtime=Integer.valueOf(a8[1]);
        int fortime1=y*10000+m*100+d;
        int backtime1=h*10000+mi*100+s;
        if(fortime1>fortime) {
            return 0;
        }else if(fortime1<fortime) {
            return 1;
        }else {
            if(backtime1>backtime) {
                return 0;
            }else {
                return 1;
            }
        }
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=this.getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.order,fragment);
        transaction.commit();
    }
}
