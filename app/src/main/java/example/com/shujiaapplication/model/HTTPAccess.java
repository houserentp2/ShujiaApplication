package example.com.shujiaapplication.model;

import com.google.gson.Gson;

import example.com.shujiaapplication.ui.Building;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HTTPAccess {

    public static final MediaType JSON=MediaType.get("application/json; charset=utf-8");
    public static int houseid = 0;

    public static int puthouse(Building building){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Gson gson=new Gson();
                    RequestBody requestBody=RequestBody.create(JSON,gson.toJson(building));
                    Request request=new Request.Builder()
                            .url("http://192.168.43.57:1323/login")
                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    houseid =gson.fromJson(responseData,int.class);//返回的数据

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        try {
            t.start();
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return houseid;
    }
}
