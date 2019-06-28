package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class SearchData extends Data {
    @SerializedName("userid")
    private String userid;
    @SerializedName("token")
    private String token;
    @SerializedName("inDate")
    private String inDate;
    @SerializedName("outDate")
    private String outDate;
    @SerializedName("city")
    private String city;
    @SerializedName("houseType")
    private int houseType;
    @SerializedName("chooseType")
    private int chooseType;

    public SearchData(String id,String t,String in,String out,String c,int ht,int ct){
        userid = id;
        token = t;
        inDate = in;
        outDate = out;
        city = c;
        houseType = ht;
        chooseType = ct;
    }

    public String getToken() {
        return token;
    }

    public String getUserid() {
        return userid;
    }

    public int getChooseType() {
        return chooseType;
    }

    public int getHouseType() {
        return houseType;
    }

    public String getCity() {
        return city;
    }

    public String getInDate() {
        return inDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setChooseType(int chooseType) {
        this.chooseType = chooseType;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setHouseType(int houseType) {
        this.houseType = houseType;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }
}
