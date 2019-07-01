package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class PersonInfoData  extends Data{
    @SerializedName("userid")
    private String userid;
    @SerializedName("token")
    private String token;
    @SerializedName("icon")
    private String user_head;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("id")
    private String id;
    @SerializedName("resident")
    private Resident resident;

    public PersonInfoData(String u,String t,String user_head,String n,String i,Resident resident){
        this.userid = u;
        this.token = t;
        this.user_head = user_head;
        this.nickname = n;
        this.id = i;
        this.resident = resident;
    }


    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
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
