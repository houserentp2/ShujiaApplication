package example.com.shujiaapplication.ui;

import android.media.session.MediaSession;

public class GetRentHouse extends Data{
    private String userid;
    private String token;
    public GetRentHouse(String a,String b){
        setUserid(a);
        setToken(b);
    }
    public void setUserid(String a){this.userid=a;}
    public void setToken(String a){this.token=a;}
    public String getUserid(){return userid;}
    public String getToken(){return token;
    }
}
