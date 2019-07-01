package example.com.shujiaapplication.ui;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import example.com.shujiaapplication.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class PersonalInfoActivity extends BaseActivity {
    private Button return_myfragment;
    private Button PIsave;
    private EditText PIHome;
    private CardView PIcardview;
    private EditText PICity;
    private ImageView myBackGround;
    private EditText PIeditName;
    private LinearLayout processBar;
    private EditText PIIDNum;
    private CircleImageView userHead;
    private CityPicker cityPicker;
    private static final int SETINFO = 0;
    private static final int GETINFO = 1;
    private static String imagePath = "";
    private static String imageCode = "";
    private static String responseData = "";
    public static final MediaType JSON=MediaType.get("application/json; charset=utf-8");
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SETINFO:{
                    processBar.setVisibility(View.GONE);
                    return_myfragment.setVisibility(View.VISIBLE);
                    myBackGround.setVisibility(View.VISIBLE);
                    userHead.setVisibility(View.VISIBLE);
                    PIcardview.setVisibility(View.VISIBLE);
                    Toast.makeText(PersonalInfoActivity.this,"加载成功",Toast.LENGTH_SHORT).show();

                    SharedPreferences preferences = getSharedPreferences("requestData",MODE_PRIVATE);
                    responseData = preferences.getString("requestGetData","");
                    Gson gson = new Gson();
                    PersonInfoData p = gson.fromJson(responseData,PersonInfoData.class);
                    if(p!=null&&p.getResident()!=null){
                        PIeditName.setText(p.getNickname());
                        PIIDNum.setText(p.getId());
                        PICity.setText(p.getResident().getProvince()+"\t"+p.getResident().getCity()+"\t"+p.getResident().getZone());
                        PIHome.setText(p.getResident().getPath());
                        userHead.setImageBitmap(getPictureByBitmap(p.getUser_head()));
                        imageCode = p.getUser_head();
                    }else{
                        userHead.setImageResource(R.drawable.user);
                    }
                    break;
                }

                case GETINFO:{
                    SharedPreferences preferences = getSharedPreferences("requestData",MODE_PRIVATE);
                    responseData = preferences.getString("requestGetData","");
                    if(responseData.equals("Update Success")){
                        Toast.makeText(PersonalInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(PersonalInfoActivity.this,responseData,Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initControl();
        setOldInfo();
        notKorad(PICity);
        PIsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String  detailHome = PIHome.getText().toString();
                        String realName = PIeditName.getText().toString();
                        String IDnum = PIIDNum.getText().toString();
                        Resident resident = new Resident(PICity.getText().toString().split("\t")[0],PICity.getText().toString().split("\t")[1],PICity.getText().toString().split("\t")[2],detailHome);
                        PersonInfoData personInfoData=new PersonInfoData(AuthInfo.userid,AuthInfo.token,imageCode,realName,IDnum,resident);
                        RequsetData.requestData(personInfoData,"userinfo");
                        Message message = new Message();
                        message.what =GETINFO;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

        return_myfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PersonalInfoActivity.this,HomePageActivity.class);
                startActivity(intent1);
            }
        });

        userHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(PersonalInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PersonalInfoActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }  else{
                    openAlbum();
                }
            }
        });
    }

    public Bitmap getPictureByBitmap(String picture){
        Bitmap bit = null;
        try {
            byte[] bytes = Base64.decode(picture, Base64.DEFAULT);
            bit = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            return bit;
        } catch (Exception e) {
            return null;
        }
    }


    public String bitmapToString(Bitmap bitmap){
        //将Bitmap转换成字符串
        String string=null;
        ByteArrayOutputStream bStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bStream);
        byte[]bytes=bStream.toByteArray();
        string=Base64.encodeToString(bytes,Base64.DEFAULT);
        return string;
    }

    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:{
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast.makeText(PersonalInfoActivity.this,"您拒绝了权限",Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 2:{
                if(resultCode==RESULT_OK){
                    if(Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat(data);
                    }else{
                        handleImageBeforeKitKat(data);
                    }
                }
            }
        }
    }

    private void handleImageOnKitKat(Intent data){
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(PersonalInfoActivity.this,uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }else if("content".equalsIgnoreCase(uri.getScheme())){
                imagePath = getImagePath(uri,null);
            }else if("file".equalsIgnoreCase(uri.getScheme())){
                imagePath = uri.getPath();
            }
            disPlayImage(imagePath);
        }
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        disPlayImage(imagePath);
    }

    private String getImagePath(Uri uri,String selection){
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return  path;
    }

    private void disPlayImage(String imagePath){
        if(imagePath!=null){
            Bitmap bitmap = matrix(BitmapFactory.decodeFile(imagePath));
            imageCode = bitmapToString(bitmap);
            userHead.setImageBitmap(bitmap);
        }else{
            Toast.makeText(PersonalInfoActivity.this,"没有得到图片",Toast.LENGTH_SHORT).show();
        }
    }



    public void setOldInfo(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DiscountData discountData=new DiscountData(AuthInfo.userid,AuthInfo.token);
                RequsetData.requestData(discountData,"getuserinfo");
                Message message = new Message();
                message.what = SETINFO;
                handler.sendMessage(message);
            }
        }).start();
    }

    public static Bitmap matrix(Bitmap bit) {                                                       //图片压缩
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 0.5f);
        Bitmap bitmap = Bitmap.createBitmap(bit, 0, 0, bit.getWidth(),
                bit.getHeight(), matrix, true);
        return bitmap;
    }

    public void initControl(){
        userHead = (CircleImageView)findViewById(R.id.user_head);
        return_myfragment = (Button)findViewById(R.id.return_myfragment);
        PIsave = (Button)findViewById(R.id.PIsave);
        PIHome = (EditText)findViewById(R.id.PIHome);
        PICity = (EditText)findViewById(R.id.PICity);
        PIeditName = (EditText)findViewById(R.id.PIeditName);
        PIIDNum = (EditText)findViewById(R.id.PIIDNum);
        processBar = (LinearLayout)findViewById(R.id.progress_bar);
        myBackGround = (ImageView)findViewById(R.id.mybackground);
        PIcardview = (CardView)findViewById(R.id.PIcardview);
    }

    public void notKorad(EditText editText){
        editText.setInputType(InputType.TYPE_NULL);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    initCityPicker();
                    cityPicker.show();
                }
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCityPicker();
                cityPicker.show();
            }
        });
    }

    public void initCityPicker() {
        cityPicker = new CityPicker.Builder(PersonalInfoActivity.this)
                .textSize(20)//滚轮文字的大小
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                .textColor(Color.parseColor("#000000"))//滚轮文字的颜色
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();

        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                String province = citySelected[0];
                String city = citySelected[1];
                String district = citySelected[2];
                String code = citySelected[3];
                PICity.setText(province +"\t" +city +"\t"+ district);
            }

            @Override
            public void onCancel() {
            }
        });
    }
}