package example.com.shujiaapplication.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingCrop;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.impl.Base64Util;
import com.bilibili.boxing.impl.BoxingGlideLoader;
import com.bilibili.boxing.impl.BoxingUcrop;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.bilibili.boxing.model.BoxingManager;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.ImageCompressor;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bilibili.boxing_impl.view.SpacesItemDecoration;
import com.google.gson.Gson;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import example.com.shujiaapplication.R;
import pub.devrel.easypermissions.EasyPermissions;

//import com.bilibili.boxing.impl.BoxingGlideLoader;
//import com.bilibili.boxing.impl.BoxingUcrop;

public class AddHouseActivity extends BaseActivity implements View.OnClickListener,EasyPermissions.PermissionCallbacks{

    private String[] permissions={Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int REQUEST_CODE = 1024;
    private static final int COMPRESS_REQUEST_CODE = 2048;
    private ArrayList<BaseMedia> medias;
    private List<String> pictures;
    private int numofPictures;
    private RecyclerView mRecyclerView;

//    private File[] files;

    private MediaResultAdapter mAdapter;
    public LinkedList<String> ImagesBag=new LinkedList<String>();
    //Building building;
    private static String responseData = "";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("requestData", Context.MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                if(!responseData.equals("")){
                    Toast.makeText(MyApplication.getContext(),"成功!",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(MyApplication.getContext(), ShowBuildListActivity.class);
//                    intent.putExtra("houseInformation",responseData);
//                    MyApplication.getContext().startActivity(intent);
                }else{
                    Toast.makeText(MyApplication.getContext(),responseData,Toast.LENGTH_SHORT).show();
                }
            }
            else if(msg.what == 1){
                SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("requestData", Context.MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                Log.e("AddHouseActivity","--------------------------------"+responseData);
                if(responseData.contains("success")){
                    Gson gson = new Gson();
                    ImageBackData img =  gson.fromJson(responseData,ImageBackData.class);
                    pictures.add(img.getData().getUrl());
                    if(pictures.size() == numofPictures){
                        sendMyMessage();
                    }
                }
                else{

                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getPermission();
        pictures = new ArrayList<String>();
        IBoxingMediaLoader loader=new BoxingGlideLoader();
        BoxingMediaLoader.getInstance().init(loader);
        BoxingCrop.getInstance().init(new BoxingUcrop());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);
        findViewById(R.id.btn_add_picture).setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.media_recycle_view);
        mAdapter = new MediaResultAdapter();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(8));
        //mRecyclerView.setOnClickListener(this);

        String [] data = new String[]{"1","2","3","4","5"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,data);
        Spinner s = (Spinner)findViewById(R.id.editShi);
        s.setAdapter(adapter);

        s = (Spinner)findViewById(R.id.editTing);
        s.setAdapter(adapter);

        Button button = findViewById(R.id.btn_subscribe);
        button.setOnClickListener(this);
        button = findViewById(R.id.btn_add_picture);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_picture:
//                BoxingConfig singleImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG).withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
//                Boxing.of(singleImgConfig).withIntent(this, BoxingActivity.class).start(this, COMPRESS_REQUEST_CODE);
                //BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).needCamera(R.drawable.ic_boxing_default_image).needGif();
                BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).needGif();
                Boxing.of(config).withIntent(this, BoxingActivity.class).start(this, REQUEST_CODE);
                break;
            case R.id.btn_subscribe:
                if(checkReg()){
                    Toast.makeText(AddHouseActivity.this,"正在提交...",Toast.LENGTH_SHORT).show();
                    numofPictures = mRecyclerView.getChildCount();
                    //TODO 提交
                    for(BaseMedia bm : medias){
                        File file = new File(bm.getPath());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                RequsetData.requestImageData(file);
                                Message message = new Message();
                                message.what = 1;
                                handler.sendMessage(message);
                            }
                        }).start();
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                    //数据是使用Intent返回
                    Intent intent = getIntent();
                    //把返回数据存入Intent
                    intent.putExtra("refresh", "true");
                    //设置返回数据
                    AddHouseActivity.this.setResult(RESULT_OK, intent);
                    //关闭Activity
                    finish();

                }
            default:
        }
    }

    private void sendMyMessage(){
        EditText editText = findViewById(R.id.editprice);
        String price = editText.getText().toString();
        editText = findViewById(R.id.editsquare);
        String square = editText.getText().toString();
        //时间
        Date now=new Date();
        //指定格式化格式
        //"2019-06-26T21:56:02.455+08:00"
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String timeStr = f.format(now);
        timeStr = timeStr.substring(0, timeStr.length()-2)+":"+timeStr.substring(timeStr.length()-2,timeStr.length());

        Spinner spinner = (Spinner)findViewById(R.id.editShi);
        int shi = Integer.parseInt( spinner.getSelectedItem().toString());
        spinner = (Spinner)findViewById(R.id.editTing);
        int ting = Integer.parseInt( spinner.getSelectedItem().toString());

        editText = findViewById(R.id.edittitle);
        String title = editText.getText().toString();
        editText = findViewById(R.id.editdiscription);
        String discription = editText.getText().toString();
        editText = findViewById(R.id.editprovince);
        String province = editText.getText().toString();
        editText = findViewById(R.id.editcity);
        String city = editText.getText().toString();
        editText = findViewById(R.id.editzone);
        String zone = editText.getText().toString();
        editText = findViewById(R.id.editpath);
        String path = editText.getText().toString();

        CheckBox checkBox = findViewById(R.id.havewater);
        int water = checkBox.isChecked() ? 1:0;
        checkBox = findViewById(R.id.havepower);
        int power = checkBox.isChecked() ? 1:0;
        checkBox = findViewById(R.id.havenet);
        int net = checkBox.isChecked() ? 1:0;
        checkBox = findViewById(R.id.havehot);
        int hot = checkBox.isChecked() ? 1:0;
        checkBox = findViewById(R.id.haveaircon);
        int aircon = checkBox.isChecked() ? 1:0;
        checkBox = findViewById(R.id.havebus);
        int bus = checkBox.isChecked() ? 1:0;
        checkBox = findViewById(R.id.shortx);
        int shortx = checkBox.isChecked() ? 1:0;
        checkBox = findViewById(R.id.longx);
        int longx = checkBox.isChecked() ? 1:0;

        editText = findViewById(R.id.editcapacity);
        int capacity = Integer.parseInt(editText.getText().toString());

//                    int n = mRecyclerView.getChildCount();
//                    String[] pictures = new String[n];
//                    for (int i = 0; i < n; i++){
//                        ImageView imageView = (ImageView) mRecyclerView.getChildAt(i);
//                        Drawable d = imageView.getDrawable();
//                        pictures[i] = Base64Util.DrawableToBase64(d);
//                    }
        //OthersData others = new OthersData(water,power,net, hot,aircon, bus, shortx, longx,capacity,comments,living,tolive,lived);

        Building building = new Building(
                AuthInfo.userid,
                AuthInfo.token,
                "",
                timeStr,
                price,
                square,
                shi,
                ting,
                title,
                discription,
                province,
                city,
                zone,
                path,
                pictures.toArray(new String[pictures.size()]),
                water,
                power,
                net,
                hot,
                aircon,
                bus,
                shortx,
                longx,
                capacity,
                new CommentsData[0],
                0,
                0,
                0
        );

        sendBuildingMessage(building);
    }

    private void sendBuildingMessage(Building building) {


//        HouseRequestData h = new HouseRequestData(AuthInfo.userid,AuthInfo.token,building.getHouseid());
        //int houseid;
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequsetData.requestData(building,"puthouse");

                //RequsetData.requestImageData("puthouse","fileName",);

                Message message = new Message();
                message.what = 0;
                handler.sendMessage(message);
            }
        }).start();
    }

    private boolean checkReg(){
        boolean flag = true;

        EditText editText = findViewById(R.id.editsquare);
        String regx = "[1-9][0-9]*";
        if(!Pattern.matches(regx,editText.getText()))
        {
            flag = false;
            editText.setError("格式错误");
        }
        editText = findViewById(R.id.editprice);
        if(!Pattern.matches(regx,editText.getText()))
        {
            flag = false;
            editText.setError("格式错误");
        }
        editText = findViewById(R.id.editcapacity);
        if(!Pattern.matches(regx,editText.getText()))
        {
            flag = false;
            editText.setError("格式错误");
        }
        if(mRecyclerView.getChildCount() < 4){
            flag = false;
            Toast.makeText(AddHouseActivity.this,"至少需要四张图片",Toast.LENGTH_SHORT).show();
        }
        //editText = findViewById(R.id.)
        //regx = "^[\\u4e00-\\u9fa5]*$ ";
        return flag;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (mRecyclerView == null || mAdapter == null) {
                return;
            }
            mRecyclerView.setVisibility(View.VISIBLE);
            medias = Boxing.getResult(data);



            if (requestCode == REQUEST_CODE) {
                mAdapter.setList(medias);
            } else if (requestCode == COMPRESS_REQUEST_CODE) { //图片单选
                final List<BaseMedia> imageMedias = new ArrayList<>(1);
                BaseMedia baseMedia = medias.get(0);
                if (!(baseMedia instanceof ImageMedia)) {
                    return;
                }

                final ImageMedia imageMedia = (ImageMedia) baseMedia;
                // the compress task may need time
                if (imageMedia.compress(new ImageCompressor(this))) {
                    imageMedia.removeExif();
                    imageMedias.add(imageMedia);
                    mAdapter.setList(imageMedias);
                }
            }
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this,"权限获取成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this,"请同意相关权限",Toast.LENGTH_SHORT).show();
    }
    private void getPermission(){
        if(EasyPermissions.hasPermissions(this,permissions)){
            //已经打开权限
            //Toast.makeText(this,"已经申请相关权限",Toast.LENGTH_SHORT).show();
        }else {
            //没有权限，申请权限
            EasyPermissions.requestPermissions(this,"需要申请相册权限",1,permissions);
        }
    }

    private class MediaResultAdapter extends RecyclerView.Adapter {
        private ArrayList<BaseMedia> mList;

        MediaResultAdapter() {
            mList = new ArrayList<>();
        }

        void setList(List<BaseMedia> list) {
            if (list == null) {
                return;
            }
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }

        List<BaseMedia> getMedias() {
            if (mList == null || mList.size() <= 0 || !(mList.get(0) instanceof ImageMedia)) {
                return null;
            }
            return mList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_boxing_simple_media_item, parent, false);
            int height = parent.getMeasuredHeight() / 4;
            view.setMinimumHeight(height);
            return new MediaViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MediaViewHolder) {
                MediaViewHolder mediaViewHolder = (MediaViewHolder) holder;
                mediaViewHolder.mImageView.setImageResource(BoxingManager.getInstance().getBoxingConfig().getMediaPlaceHolderRes());
                BaseMedia media = mList.get(position);
                String path;
                if (media instanceof ImageMedia) {
                    path = ((ImageMedia) media).getThumbnailPath();
                } else {
                    path = media.getPath();
                }
                //BoxingMediaLoader.getInstance().displayThumbnail(mediaViewHolder.mImageView, path, 150, 150);

//                try{
//                    Method method=null;
//                    method= BoxingMediaLoader.getInstance().getLoader().getClass().getMethod("displayThumbnailandTran", ImageView.class, LinkedList.class,String.class,int.class,int.class);
//                    method.invoke(BoxingMediaLoader.getInstance().getLoader(),mediaViewHolder.mImageView,ImagesBag, path, 150, 150);
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
                BoxingMediaLoader.getInstance().displayThumbnail(mediaViewHolder.mImageView, path, 150, 150);

            }
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

    }
    private class MediaViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        MediaViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.media_item);
        }
    }

}
