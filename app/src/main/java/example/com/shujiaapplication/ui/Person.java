package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class Person {
    @SerializedName("userid")
    public String userid;
    @SerializedName("token")
    public String token;

    public void saveAuth(){
        AuthInfo.userid=this.userid;
        AuthInfo.token=this.token;
    }
}
