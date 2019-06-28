package example.com.shujiaapplication.ui;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Building extends Data implements Serializable {
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
    private int pay;
    @SerializedName("view")
    private int view;
    @SerializedName("live")
    private int live;
    @SerializedName("sign")
    private int sign;
    @SerializedName("apply")
    private int apply;
    @SerializedName("finish")
    private int finish;
    @SerializedName("others")
    private OthersData others;
    @SerializedName("symbol")
    private int symbol;

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
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

    public int getApply() {
        return apply;
    }

    public int getFinish() {
        return finish;
    }

    public int getLive() {
        return live;
    }

    public int getPay() {
        return pay;
    }

    public int getSign() {
        return sign;
    }

    public int getView() {
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

    public void setView(int view) {
        this.view = view;
    }

    public void setApply(int apply) {
        this.apply = apply;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public void setLocation(Resident location) {
        this.location = location;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public void setShiting(ShiTing shiting) {
        this.shiting = shiting;
    }

    public void setSign(int sign) {
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

    public int compareTo(Building o) {
        if(Integer.parseInt(this.getPrice())>Integer.parseInt(o.getPrice())){
            return 1;
        }else if(this.getPrice()==o.getPrice()){
            return 0;
        }else{
            return -1;
        }
    }
}
