package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class CheckHouseData extends Data {
    @SerializedName("userid")
    private String userid;
    @SerializedName("token")
    private String token;
    @SerializedName("houseid")
    private String houseid;

    public CheckHouseData(String id,String t,String hid){
        setHouseid(hid);
        setUserid(id);
        setToken(t);
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }

    public String getToken() {
        return token;
    }

    public String getUserid() {
        return userid;
    }

    public String getHouseid() {
        return houseid;
    }

}
