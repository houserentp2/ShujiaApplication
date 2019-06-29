package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class PersonInfoData  extends Data{
    @SerializedName("userid")
    private String userid;
    @SerializedName("token")
    private String token;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("id")
    private String id;
    @SerializedName("resident")
    private Resident resident;

    public PersonInfoData(String u,String t,String n,String i,Resident resident){
        this.userid = u;
        this.token = t;
        this.nickname = n;
        this.id = i;
        this.resident = resident;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Resident getResident() {
        return resident;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }
}
