package example.com.shujiaapplication.ui;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import example.com.shujiaapplication.R;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.ViewHolder>{
    private List<Building> mBuildingList;
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
    public BuildingAdapter(List<Building> buildingList){
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
        Building building =mBuildingList.get(positon);
        holder.buildingImage.setImageBitmap();
        holder.buildingMessage.setText(building.getLocation().getPath());
        int a=building.getSymbol();
        switch(a){
            case 1:
                holder.buildingTips.setBackgroundColor(Color.RED);
                holder.buildingTips.setText("去支付");
                break;
            case 2:
                holder.buildingTips.setBackgroundColor(Color.YELLOW);
                holder.buildingTips.setText("入住");
                holder.buildingTips.setTextColor(Color.BLACK);
                break;
            case 3:
                holder.buildingTips.setBackgroundColor(Color.GREEN);
                holder.buildingTips.setText("去评价");
                holder.buildingTips.setTextColor(Color.BLACK);
                break;
            default:
                break;
        }
    }
    @Override
    public int getItemCount() {
        return mBuildingList.size();
    }

}

