package example.com.shujiaapplication.ui.MainFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
import example.com.shujiaapplication.ui.BuildingLiving;
import example.com.shujiaapplication.ui.BuildingPay;
import example.com.shujiaapplication.ui.BuildingView;
import example.com.shujiaapplication.ui.GetHouseInfo;
import example.com.shujiaapplication.ui.HomePageActivity;
import example.com.shujiaapplication.ui.LoginData;
import example.com.shujiaapplication.ui.MainActivity;
import example.com.shujiaapplication.ui.MyApplication;
import example.com.shujiaapplication.ui.NewBuilding;
import example.com.shujiaapplication.ui.OnRecyclerItemClickListener;
import example.com.shujiaapplication.ui.RequsetData;


public class OrderFragmentS1 extends Fragment implements  View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View mview;
    private List<NewBuilding> buildingList=new ArrayList<>();
    private List<BuildingListData>buildingList2=new ArrayList<>();
    private List<NewBuilding> buildingList3=new ArrayList<>();
    private String houseid;
    private BuildingListData buildinglistdata;
    public OrderFragmentS1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragmentS2.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragmentS1 newInstance(String param1, String param2) {
        OrderFragmentS1 fragment = new OrderFragmentS1();
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
        mview=inflater.inflate(R.layout.fragment_order_fragment_s1, container, false);
        RecyclerView recyclerView=(RecyclerView)mview.findViewById(R.id.view_all);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Log.e("Adapters1","newbuildingnumber11111_________________"+buildingList3.size());
        BuildingAdapter adapter=new BuildingAdapter(buildingList3);
        adapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int Position, List<NewBuilding> buildingList) {
                String start=buildingList.get(Position).getStart();
                String stop=buildingList.get(Position).getStop();
                NewBuilding newbuild=buildingList.get(Position);
                int getpaied=Integer.valueOf(newbuild.getResult());
                if(getpaied==1){
                    Intent intent=new Intent(getActivity(), BuildingPay.class);
                    intent.putExtra("nbuild",buildingList.get(Position));
                    startActivity(intent);
                }else{
                    if(isLaterToLocalTime(start)==1){

                    }else{
                        if(isLaterToLocalTime(stop)==1){
                            Intent intent=new Intent(getActivity(), BuildingLiving.class);
                            intent.putExtra("nbuild",buildingList.get(Position));
                            startActivity(intent);
                        }else{
                            Intent intent=new Intent(getActivity(), BuildingView.class);
                            intent.putExtra("nbuild",buildingList.get(Position));
                            startActivity(intent);
                        }
                    }
                }
            }
        });
        recyclerView.setAdapter(adapter);
        return mview;
    }
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        Button a=mview.findViewById(R.id.button_pay);
        Button b=mview.findViewById(R.id.button_live);
        Button c=mview.findViewById(R.id.button_view);
        Button d=mview.findViewById(R.id.button_rightno);
        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);
    }
    public void onClick(View view){
        switch(view.getId()){
            case R.id.button_pay:
                OrderFragmentS2 a=new OrderFragmentS2();
                replaceFragment(a);
                break;
            case R.id.button_live:
                OrderFragmentS3 b=new OrderFragmentS3();
                replaceFragment(b);
                break;
            case R.id.button_view:
                OrderFragmentS4 c=new OrderFragmentS4();
                replaceFragment(c);
                break;
            case R.id.button_rightno:
                OrderFragmentL1 d=new OrderFragmentL1();
                replaceFragment(d);
                break;
            default:
                break;
        }
    }
    public void initBuildings(){
        setBuildingList(AuthInfo.getBuildingList());
        setBuildingList2(AuthInfo.getBuildingList2());
        Log.e("OrderFragments1","newbuildingnumber_________________"+buildingList2.size());
        for(NewBuilding building:buildingList) {
            houseid = building.getHouseid();
            Log.e("OrderFragments1","houseid________________"+houseid);
            for (BuildingListData buildingListData : buildingList2) {
                Log.e("OrderFragments1","buildinglistid_________________"+buildingListData.getHouseid());
                if (houseid.equals(buildingListData.getHouseid())) {
                    BuildingListData b = buildingListData;
                    String start = building.getStart();
                    String stop = building.getStop();
                    int getpaied = Integer.valueOf(building.getResult());
                    if (b.getOthers().getShortx() == 1) {
                        buildingList3.add(building);
                    }
                }
            }
        }
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
    public void setBuildingList(List<NewBuilding> a){
        this.buildingList=a;
    }
    public void setBuildingList2(List<BuildingListData> a){
        this.buildingList2=a;
    }
    public List<NewBuilding> getBuildingList(){
        return buildingList;
    }
    public List<BuildingListData> getBuildingList2(){
        return buildingList2;
    }
}
