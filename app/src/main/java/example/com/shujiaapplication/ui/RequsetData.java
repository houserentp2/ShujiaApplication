package example.com.shujiaapplication.ui;

import android.widget.Toast;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequsetData {
    public static final MediaType JSON=MediaType.get("application/json; charset=utf-8");
    private static String responseData = "";
    public static String requestData(Data d,String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Gson gson=new Gson();
                    RequestBody requestBody=RequestBody.create(JSON,gson.toJson(d));
                    Request request=new Request.Builder()
                            .url("http://192.168.43.57:1323/"+url)
                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                     responseData=response.body().string();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        return responseData;
    }
}
