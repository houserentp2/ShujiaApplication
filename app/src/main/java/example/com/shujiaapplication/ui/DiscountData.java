package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class DiscountData extends Data{
    @SerializedName("userid")
    private String userid;
    @SerializedName("token")
    private String token;

    public DiscountData(String u,String t){
        this.userid = u;
        this.token = t;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
