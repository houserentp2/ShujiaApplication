package example.com.shujiaapplication.ui.MainFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import example.com.shujiaapplication.R;


public class OrderFragmentS3 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mview;

    public OrderFragmentS3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragmentS3.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragmentS3 newInstance(String param1, String param2) {
        OrderFragmentS3 fragment = new OrderFragmentS3();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mview=inflater.inflate(R.layout.fragment_order_fragment_s3, container, false);
        return mview;
    }
    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        Button a=mview.findViewById(R.id.button_all);
        Button b=mview.findViewById(R.id.button_pay);
        Button c=mview.findViewById(R.id.button_view);
        Button d=mview.findViewById(R.id.button_rightno);
        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);
    }
    public void onClick(View view){
        switch(view.getId()){
            case R.id.button_all:
                replaceFragment(new OrderFragmentS1());
                break;
            case R.id.button_pay:
                replaceFragment(new OrderFragmentS2());
                break;
            case R.id.button_view:
                replaceFragment(new OrderFragmentS4());
                break;
            case R.id.button_rightno:
                replaceFragment(new OrderFragmentL1());
                break;
            default:
                break;
        }
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=this.getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.order,fragment);
        transaction.commit();
    }

}
