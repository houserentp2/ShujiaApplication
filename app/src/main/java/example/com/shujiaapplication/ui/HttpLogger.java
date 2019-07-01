package example.com.shujiaapplication.ui;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    private OkHttpClient mOkHttpClient;
    @Override
    public void log(String message) {
        Log.d("HttpLogInfo", message);
    }
    private OkHttpClient okhttpclient() {
        if (mOkHttpClient == null) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .addNetworkInterceptor(logInterceptor)
                    .build();
        }
        return mOkHttpClient;
    }

    public OkHttpClient getmOkHttpClient() {
        return mOkHttpClient;
    }
}
