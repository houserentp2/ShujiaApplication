package example.com.shujiaapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import example.com.shujiaapplication.R;

import static example.com.shujiaapplication.ui.MyApplication.getContext;

public class ShowBuildAdapter extends RecyclerView.Adapter<ShowBuildAdapter.ViewHolder> {
    private List<BuildingListData> mBuildList;
    private Context mContext;
    private static String responseData = "";

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                SharedPreferences preferences = getContext().getSharedPreferences("requestData",Context.MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                if(responseData.contains("userid")){
                    Toast.makeText(getContext(),"成功!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), HouseInfomationActivity.class);
                    intent.putExtra("houseInformation",responseData);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(intent);
                }else{
                    Toast.makeText(getContext(),"获取详情失败",Toast.LENGTH_SHORT).show();
                    Log.e("ShowBuildAda","!!!!!!!!!!!!!!!!!!!!!"+responseData);
                }
            }
        }
    };

    static class ViewHolder extends RecyclerView.ViewHolder{
        View buildView;
        ImageView building_image;
        TextView building_title;
        TextView building_price;
        TextView building_details;
        RelativeLayout building_item;

        public ViewHolder(View view){
            super(view);
            buildView = view;
            building_item = (RelativeLayout)view.findViewById(R.id.building_item);
            building_image = (ImageView)view.findViewById(R.id.building_image);
            building_title = (TextView)view.findViewById(R.id.building_title);
            building_price = (TextView)view.findViewById(R.id.building_price);
            building_details = (TextView)view.findViewById(R.id.building_details);
        }
    }

    public ShowBuildAdapter(List<BuildingListData> buildings){
        mBuildList = buildings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext==null){
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.building_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.building_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                BuildingListData building = mBuildList.get(position);

                HouseRequestData h = new HouseRequestData(AuthInfo.userid,AuthInfo.token,building.getHouseid());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RequsetData.requestData(h,"gethouse");
                        Message message = new Message();
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BuildingListData building = mBuildList.get(i);
        String s = building.getPicture();
        Glide.with(viewHolder.building_image.getContext())
                .load(s)
                .centerCrop()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(viewHolder.building_image);


//        viewHolder.building_image.setImageBitmap(resizeBitmap(building.getPictureByBitmap()));
        viewHolder.building_title.setText(building.getTitle());
        viewHolder.building_price.setText("¥"+building.getPrice());
        viewHolder.building_details.setText(building.getShiting().getShi()+"室"+building.getShiting().getTing()+"厅 |宜居"+building.getSquare()+"m²");
    }

    private Bitmap resizeBitmap(Bitmap picture){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager WM = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        WM.getDefaultDisplay().getMetrics(dm);
        int newWidth = dm.widthPixels;
        int newHeight = 810;
        int bitwidth = picture.getWidth();
        int bitheight = picture.getHeight();
        float scaleWidth = ((float) newWidth) / bitwidth;
        float scaleHeight = ((float) newHeight) / bitheight;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        picture = Bitmap.createBitmap(picture, 0, 0, bitwidth, bitheight, matrix, true);
        Bitmap bitmap = Bitmap.createBitmap(picture, 0,0, dm.widthPixels, newHeight);
        return bitmap;
    }

    @Override
    public int getItemCount() {
        return mBuildList.size();
    }
}
