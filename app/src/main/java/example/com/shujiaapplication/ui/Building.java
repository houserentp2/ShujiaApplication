package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Building implements Serializable {

    @SerializedName("userid")
    private String userid;
    @SerializedName("headpicture")
    private String headpicture;
    @SerializedName("token")
    private String token;
    @SerializedName("houseid")
    private String houseid;
    @SerializedName("time")
    private String time;
    @SerializedName("price")
    private String price;
    @SerializedName("square")
    private String square;

    @SerializedName("shiting")
    private ShiTing shiting;
    @SerializedName("title")
    private String title;

    @SerializedName("location")
    private Resident location;
    @SerializedName("description")
    private String description;
    @SerializedName("pictures")
    private String[] pictures;
    @SerializedName("pay")
    private String pay;
    @SerializedName("view")
    private String view;
    @SerializedName("live")
    private String live;
    @SerializedName("sign")
    private String sign;
    @SerializedName("apply")
    private String apply;
    @SerializedName("finish")
    private String finish;
    @SerializedName("others")
    private OthersData others;
    @SerializedName("symbol")
    private String symbol;


//    public Building(String userid, String token, String houseid,String time, String price, String square,
//                    String shi, String ting, String title,String description,
//                    String province, String city, String zone, String path, String[] pictures){
//        setUserid(userid);
//        setToken(token);
//        setHouseid(houseid);
//        setTime(time);
//        setPrice(price);
//        setSquare(square);
//        this.shiting = new ShiTing(shi,ting);
//        setTitle(title);
//        setDescription(description);
//        this.location = new Resident(province, city, zone, path);
//        setPictures(pictures);
//    }



    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getHeadpicture() {
        return headpicture;
    }

    public void setHeadpicture(String headpicture) {
        this.headpicture = headpicture;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getToken() {
        return token;
    }

    public OthersData getOthers() {
        return others;
    }

    public String[] getPictures() {
        return pictures;
    }

    public String getApply() {
        return apply;
    }

    public String getFinish() {
        return finish;
    }

    public String getLive() {
        return live;
    }

    public String getPay() {
        return pay;
    }

    public String getSign() {
        return sign;
    }

    public String getView() {
        return view;
    }

    public Resident getLocation() {
        return location;
    }

    public String getHouseid() {
        return houseid;
    }

    public String getSquare() {
        return square;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getUserid() {
        return userid;
    }

    public ShiTing getShiting() {
        return shiting;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOthers(OthersData others) {
        this.others = others;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setView(String view) {
        this.view = view;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public void setLocation(Resident location) {
        this.location = location;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public void setShiting(ShiTing shiting) {
        this.shiting = shiting;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getPriceByInt(){
        return Integer.parseInt(price);
    }
}
