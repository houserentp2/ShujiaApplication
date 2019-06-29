package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class CheckerData extends Data {
    @SerializedName("userid")
    private String userid;
    @SerializedName("token")
    private String token;
    @SerializedName("invite")
    private String invite;

    public CheckerData(String u,String t,String i){
        setInvite(i);
        setToken(t);
        setUserid(u);
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

    public String getUserid() {
        return userid;
    }

    public String getToken() {
        return token;
    }

    public String getInvite() {
        return invite;
    }
}
