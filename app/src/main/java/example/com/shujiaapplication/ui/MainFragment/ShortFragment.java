package example.com.shujiaapplication.ui.MainFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import example.com.shujiaapplication.R;
import example.com.shujiaapplication.ui.AuthInfo;
import example.com.shujiaapplication.ui.DateChooseActivity;
import example.com.shujiaapplication.ui.DiscountData;
import example.com.shujiaapplication.ui.HomePageActivity;
import example.com.shujiaapplication.ui.LoginData;
import example.com.shujiaapplication.ui.MainActivity;
import example.com.shujiaapplication.ui.MyApplication;
import example.com.shujiaapplication.ui.RequsetData;
import example.com.shujiaapplication.ui.SearchData;
import example.com.shujiaapplication.ui.ShowBuildListActivity;

import static example.com.shujiaapplication.ui.DateChooseActivity.SHORT_CHOOSE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShortFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShortFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShortFragment extends MainFatherFragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TextView cityText;
    private TextView inDateText;
    private TextView outDateText;
    private TextView nightText;
    private TextView positionText;
    private Button searchButton;
    private static String responseData = "";

    private String[] strs;
    private String inDate;
    private String outDate;
    private String dateCount;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                SharedPreferences preferences = getActivity().getSharedPreferences("requestData",Context.MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                Log.e("ShortFragment","获取房屋列表的数据是"+responseData);
                searchButton.setEnabled(true);
                if(responseData.contains("id")){
                    Log.e("ShortFragment","房屋列表数据是"+responseData);
                    Intent intent = new Intent(MyApplication.getContext(), ShowBuildListActivity.class);
                    intent.putExtra("getHouseList",responseData);
                    startActivity(intent);
                }else{
                    Log.e("ShortFragment","!!!!!!!!!!!!!!!!!!!!返回房屋列表的是空"+responseData);
                    Toast.makeText(getActivity(),"没有符合搜索条件的房屋",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    public ShortFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShortFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShortFragment newInstance(String param1, String param2) {
        ShortFragment fragment = new ShortFragment();
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



        return inflater.inflate(R.layout.fragment_short, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initView();
        Intent intent = getActivity().getIntent();
        strs = intent.getStringArrayExtra("dateData");
        if(strs != null){
            inDate = strs[0];
            outDate = strs[1];
            dateCount = strs[2];
            inDateText.setText(inDate);
            outDateText.setText(outDate);
            nightText.setText("共"+dateCount+"晚");
        }

    }

    private void initView(){
        cityText = (TextView) getActivity().findViewById(R.id.city_name_short);
        positionText = (TextView) getActivity().findViewById(R.id.my_position_short);
        positionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityChooseClick();
            }
        });
        cityText.setOnClickListener(new CityOnClickListener());

        inDateText = (TextView) getActivity().findViewById(R.id.date_in);
        outDateText = (TextView) getActivity().findViewById(R.id.date_out);
        nightText = (TextView) getActivity().findViewById(R.id.night_date);
        searchButton = (Button) getActivity().findViewById(R.id.search_short_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchButton.setEnabled(false);
                //SearchData search = new SearchData(AuthInfo.userid,AuthInfo.token,city);
                SearchData search = new SearchData(AuthInfo.userid,AuthInfo.token,"eee");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RequsetData.requestData(search,"gethouselist");
                        Message message = new Message();
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }).start();

            }
        });
        inDateText.setOnClickListener(new DateOnClickListener());
        outDateText.setOnClickListener(new DateOnClickListener());
        nightText.setOnClickListener(new DateOnClickListener());
        cityText.setText(city);
    }

    private void dateChooseClick(){
        Intent dateIntent = new Intent(getActivity(), DateChooseActivity.class);
        dateIntent.putExtra("ChooseType",SHORT_CHOOSE);
        startActivity(dateIntent);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public class DateOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            dateChooseClick();
        }
    }
}
