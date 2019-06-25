package example.com.shujiaapplication.ui.MainFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.w3c.dom.Text;

import example.com.shujiaapplication.R;
import example.com.shujiaapplication.ui.Building;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderPay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderPay extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Building mbuild;
    private  View mview;
    public OrderPay() {
        // Required empty public constructor

    }
    public void setBuilding(Building a){
        this.mbuild=a;
    }
    public Building getBuilding(){
        return mbuild;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderPay.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderPay newInstance(String param1, String param2) {
        OrderPay fragment = new OrderPay();
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
        // Inflate the layout for this fragment
        mview=inflater.inflate(R.layout.fragment_order_pay, container, false);
        ImageView imageView=mview.findViewById(R.id.building_view1);
        imageView.setImageResource(mbuild.getPicture_id());
        return mview;
    }

}
