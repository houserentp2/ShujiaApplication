package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class CheckerResultData extends Data {
    @SerializedName("userid")
    private String userid;
    @SerializedName("token")
    private String token;
    @SerializedName("houseid")
    private String houseid;
    @SerializedName("result")
    private int result;

    public CheckerResultData(String id,String t,String hid,int r){
        setHouseid(hid);
        setUserid(id);
        setToken(t);
        setResult(r);
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

    public void setResult(int result) {
        this.result = result;
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

    public int getResult() {
        return result;
    }
}
