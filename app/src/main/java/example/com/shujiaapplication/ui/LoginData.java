package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("phonenum")
    private String newAccountName;
    @SerializedName("password")
    private String newAccountPassword;
    @SerializedName("userid")
    private String userid;
    @SerializedName("nickname")
    private String nickname;

    public LoginData(String ph,String u,String n,String p){
        this.newAccountName = ph;
        this.userid = u;
        this.nickname = n;
        this.newAccountPassword = p;

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNewAccountName() {
        return newAccountName;
    }

    public String getNewAccountPassword() {
        return newAccountPassword;
    }

    public void setNewAccountName(String newAccountName) {
        this.newAccountName = newAccountName;
    }

    public void setNewAccountPassword(String newAccountPassword) {
        this.newAccountPassword = newAccountPassword;
    }
}
