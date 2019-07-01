package example.com.shujiaapplication.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequsetData {
    public static final MediaType JSON=MediaType.get("application/json; charset=utf-8");
    private static String responseData = "";
    public static void requestData(Data d,String url){
                try{

                    OkHttpClient client = new OkHttpClient();
                    Gson gson=new Gson();
                    RequestBody requestBody=RequestBody.create(JSON,gson.toJson(d));
//                    MultipartBody b = MultipartBody

                    Request request=new Request.Builder()//http://210.42.105.207
                            .url("http://192.168.31.71:1323/"+url)   //192.168.31.71:1323
                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                    responseData=response.body().string();
                    Log.e("RequsetData", "handleMessage: "+responseData );
                    SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("requestData", Context.MODE_PRIVATE).edit();
                    editor.putString("requestGetData",responseData);
                     editor.apply();
                     if(url.equals("login")&&responseData.contains("userid")){
                         Log.e("RequsetData", "!!!!!!????????????????: "+responseData );
                         Person p =gson.fromJson(responseData,Person.class);//返回的数据
                         AuthInfo.setAuth(p.userid,p.token);
                     }
                }catch (Exception e){
                    e.printStackTrace();
                }
    }
    public static void requestImageData(File file){
        try{
            String urlx="https://sm.ms/api/upload";
            OkHttpClient client = new OkHttpClient();
            Gson gson=new Gson();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("smfile", file.getName(),
                            RequestBody.create(MediaType.parse("multipart/form-data"), file))
                    .build();
            Log.e("++++==", "requestImageData: "+file.getName());
            Log.e("++++==", "requestImageData: "+file.length());


            Request request=new Request.Builder()//http://210.42.105.207
                    .url(urlx)   //192.168.31.71:1323
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent","Mozilla/5.0 (Linux; Android 9; MI 6 Build/PKQ1.190118.001) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.109 Mobile Safari/537.36"
                    )
                    .post(requestBody)
                    .build();

            Response response=client.newCall(request).execute();
            responseData=response.body().string();
            Log.e("RequsetData", "handleMessage: "+responseData );
            SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("requestData", Context.MODE_PRIVATE).edit();
            editor.putString("requestGetData",responseData);
            editor.apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
