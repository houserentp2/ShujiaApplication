package example.com.shujiaapplication.ui;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import okhttp3.MediaType;
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
                    Request request=new Request.Builder()
                            //.url("http://210.42.105.207/"+url)
                            .url("http://192.168.31.71:1323/"+url)
                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                     responseData=response.body().string();

                     Thread.sleep(300);
                    SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("requestData", Context.MODE_PRIVATE).edit();
                    editor.putString("requestGetData",responseData);
                     editor.apply();
                     if(url.equals("login")&&responseData.contains("userid")){
                         Person p =gson.fromJson(responseData,Person.class);//返回的数据
                         AuthInfo.setAuth(p.userid,p.token);
                     }
                }catch (Exception e){
                    e.printStackTrace();
                }
    }
}
