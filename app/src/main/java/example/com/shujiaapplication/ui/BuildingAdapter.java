package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import example.com.shujiaapplication.R;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.ViewHolder>{
    private List<NewBuilding> mBuildingList;
    private OnRecyclerItemClickListener monItemClickListener;
    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener){
        monItemClickListener=listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView buildingImage;
        TextView buildingMessage;
        TextView buildingTips;
        public ViewHolder(View view){
            super(view);
            buildingImage=(ImageView)view.findViewById(R.id.building_view);
            buildingMessage=(TextView)view.findViewById(R.id.buiding_message);
            buildingTips=(TextView)view.findViewById(R.id.buiding_tips);
            view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (monItemClickListener!=null){

                                monItemClickListener.onItemClick(getAdapterPosition(),mBuildingList);
                            }
                        }
                    });
        }
    }
    public BuildingAdapter(List<NewBuilding> buildingList){
        mBuildingList=buildingList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.building,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int positon){
        NewBuilding building =mBuildingList.get(positon);
        BuildingListData a=getBuildingInformation(building.getUserid(),building.getToken(),building.getHouseid());
        holder.buildingImage.setImageBitmap(a.getPictureByBitmap());
        holder.buildingMessage.setText(a.getTitle());
        if(a.getOthers().getShortx()==1){
            String start=building.getStart();
            String stop=building.getStop();
            int getpaied=Integer.valueOf(building.getResult());
            if(getpaied==1){
                holder.buildingTips.setText("去支付");
                holder.buildingTips.setTextColor(Color.WHITE);
                holder.buildingTips.setBackgroundColor(Color.RED);
            }else{
                if(isLaterToLocalTime(start)==1){

                }else{
                    if(isLaterToLocalTime(stop)==1){
                        holder.buildingTips.setText("去入住");
                        holder.buildingTips.setTextColor(Color.BLACK);
                        holder.buildingTips.setBackgroundColor(Color.YELLOW);
                    }else{
                        holder.buildingTips.setText("去评价");
                        holder.buildingTips.setTextColor(Color.BLACK);
                        holder.buildingTips.setBackgroundColor(Color.GREEN);
                    }
                }
            }
        }

    }
    @Override
    public int getItemCount() {
        return mBuildingList.size();
    }
    public BuildingListData getBuildingInformation(String Userid, String Token, String Houseid){
        GetHouseInfo a=new GetHouseInfo(Userid,Token);
        String s= RequsetData.requestData(a,"gethouselist");
        ArrayList<BuildingListData> buildings = new ArrayList<BuildingListData>();
        Gson gson = new Gson();
        buildings = gson.fromJson(s,new TypeToken<List<BuildingListData>>(){}.getType());
        BuildingListData building=new BuildingListData();
        for(BuildingListData build:buildings){
            if(build.getHouseid().equals(Houseid)){
                building=build;
            }
        }
        return building;
    }
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
}

