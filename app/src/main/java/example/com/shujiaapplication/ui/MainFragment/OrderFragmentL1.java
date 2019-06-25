package example.com.shujiaapplication.ui.MainFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import example.com.shujiaapplication.R;
import example.com.shujiaapplication.ui.Building;
import example.com.shujiaapplication.ui.BuildingAdapter;
import example.com.shujiaapplication.ui.OnRecyclerItemClickListener;


public class OrderFragmentL1 extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mview;
    private List<Building> buildingList=new ArrayList<>();
    private List<Building> buildingList2=new ArrayList<>();

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
        List<Integer> pictures = new ArrayList<>();
        pictures.add(R.drawable.imgv_slide);
        pictures.add(R.drawable.background);
        pictures.add(R.drawable.collect);
        pictures.add(R.drawable.mybackground);
        pictures.add(R.drawable.user);
        pictures.add(R.drawable.unseen);
        Building a=new Building(1,1,1,1,1,"fuck","fuck","fuck","fuck","fuck",1,0,pictures,1,0,0);
        buildingList.add(a);
        Building b=new Building(1,1,1,1,1,"fuck","fuck","fuck","fuck","fuck",2,0,pictures,1,0,0);
        buildingList.add(b);
        Building c=new Building(1,1,1,1,1,"fuck","fuck","fuck","fuck","fuck",3,0,pictures,1,0,0);
        buildingList.add(c);
        Building d=new Building(1,1,1,1,1,"fuck","fuck","fuck","fuck","fuck",0,1,pictures,1,0,0);
        buildingList.add(d);
        Building e=new Building(1,1,1,1,1,"fuck","fuck","fuck","fuck","fuck",0,1,pictures,1,0,0);
        buildingList.add(e);
        Building f=new Building(1,1,1,1,1,"fuck","fuck","fuck","fuck","fuck",0,1,pictures,1,0,0);
        buildingList.add(f);
        for(Building building:buildingList){
            if(building.getLongsymbol()==1){
                buildingList2.add(building);
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
