package example.com.shujiaapplication.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import example.com.shujiaapplication.R;

public class ShowBuildAdapter extends RecyclerView.Adapter<ShowBuildAdapter.ViewHolder> {
    private List<Building> mBuildList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View buildView;
        ImageView building_image;
        ImageView collect_image;
        CircleImageView building_head;
        TextView building_title;
        TextView building_price;
        TextView building_details;
        RelativeLayout building_item;

        public ViewHolder(View view){
            super(view);
            buildView = view;
            collect_image = (ImageView)view.findViewById(R.id.collect_image);
            building_head = (CircleImageView)view.findViewById(R.id.building_head);
            building_item = (RelativeLayout)view.findViewById(R.id.building_item);
            building_image = (ImageView)view.findViewById(R.id.building_image);
            building_title = (TextView)view.findViewById(R.id.building_title);
            building_price = (TextView)view.findViewById(R.id.building_price);
            building_details = (TextView)view.findViewById(R.id.building_details);
        }
    }

    public ShowBuildAdapter(List<Building> buildings){
        mBuildList = buildings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.building_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.building_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Building building = mBuildList.get(position);
                Toast.makeText(v.getContext(),"你点击了第"+(position+1)+"个房源",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Building building = mBuildList.get(i);
        viewHolder.collect_image.setImageResource(building.getCollect_image());
        viewHolder.building_image.setImageResource(building.getPicture_id());
        viewHolder.building_title.setText(building.getTitle());
        viewHolder.building_head.setImageResource(building.getBuild_head());
        viewHolder.building_price.setText("¥"+building.getPrice());
        viewHolder.building_details.setText(building.getShi()+"室"+building.getTing()+"厅 |宜居"+building.getLiving_people()+"人 |"+building.getSquare()+"m²");
    }

    @Override
    public int getItemCount() {
        return mBuildList.size();
    }
}
