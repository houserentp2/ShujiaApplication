package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class HouseRequestData extends Data {
    @SerializedName("userid")
    private String userid;
    @SerializedName("token")
    private String token;
    @SerializedName("houseid")
    private String houseid;

    public HouseRequestData(String id,String t,String hid){
        userid = id;
        token = t;
        houseid = hid;
    }

    public String getHouseid() {
        return houseid;
    }

    public String getUserid() {
        return userid;
    }

    public String getToken() {
        return token;
    }

    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
