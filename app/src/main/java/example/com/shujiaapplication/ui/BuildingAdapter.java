package example.com.shujiaapplication.ui;

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
        holder.buildingImage.setImageResource(building.getPicture_id().get(0));
        holder.buildingMessage.setText(building.getPath());
        int a=building.getShortsymbol();
        switch(a){
            case 1:
                holder.buildingMessage.setText("去支付");
                break;
            case 2:
                holder.buildingMessage.setText("入住");
                break;
            case 3:
                holder.buildingMessage.setText("去评价");
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

