package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class SearchData extends Data {
    @SerializedName("userid")
    private String userid;
    @SerializedName("token")
    private String token;
    @SerializedName("queryparam")
    private String queryparam;


    public SearchData(String id,String t,String query){
        userid = id;
        token = t;
        queryparam = query;
    }

    public String getToken() {
        return token;
    }

    public String getUserid() {
        return userid;
    }

    public String getQueryparam() {
        return queryparam;
    }

    public void setQueryparam(String queryparam) {
        this.queryparam = queryparam;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


}
