package example.com.shujiaapplication.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.widget.Button;

import java.util.Random;

import example.com.shujiaapplication.R;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;

public class Code {

    private static Handler mHandler = new Handler();
    private static int daoTime = 61;
    private static String code = "";

    public static void daotime(final Button getCode){
        daoTime = daoTime-1;
        getFourCode();
        getCode.setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (daoTime<=60){
                    daoTime = daoTime-1;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(daoTime!=0){
                                getCode.setText(daoTime+"秒后重新获取");
                            } else{
                                getCode.setEnabled(true);
                                getCode.setText("获取验证码");
                                daoTime = 61;
                            }
                        }
                    });
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.fillInStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void getFourCode(){

        Random random = new Random();
        if(code.length()!=0){
            code = "";
        }
        for(int i=0;i<=3;i++){
            code = code+random.nextInt(10);
        }

        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("setCode",MODE_PRIVATE).edit();
        editor.putString("code",code);
        editor.apply();

        NotificationManager manager = (NotificationManager)MyApplication.getContext().getSystemService(NOTIFICATION_SERVICE);
        String Channel_id = "my_channel_id_01";

        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(Channel_id,"My Notification",NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Channel description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            channel.enableVibration(true);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder(MyApplication.getContext(),Channel_id);
        notification .setContentTitle("您好，您注册住多多的验证码为：")
                .setContentText(code)
                .setWhen(System.currentTimeMillis())
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(MyApplication.getContext().getResources(),R.mipmap.ic_launcher));
        manager.notify(1,notification.build());
    }

}
