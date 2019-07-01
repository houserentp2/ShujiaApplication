package example.com.shujiaapplication.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.google.gson.annotations.SerializedName;

public class BuildingListData extends Data implements Comparable<BuildingListData> {
    @SerializedName("userid")
    private String userid;
    @SerializedName("icon")
    private String icon;
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
    @SerializedName("picture")
    private String picture;
    @SerializedName("others")
    private OthersData others;


    public OthersData getOthers() {
        return others;
    }

    public void setOthers(OthersData others) {
        this.others = others;
    }

    public String getUserid() {
        return userid;
    }

    public String getIcon() {
        return icon;
    }

    public String getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getSquare() {
        return square;
    }

    public String getPicture() {
        return picture;
    }

    public String getHouseid() {
        return houseid;
    }

    public Resident getLocation() {
        return location;
    }

    public ShiTing getShiting() {
        return shiting;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setIcon(String i) {
        this.icon = i;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public void setShiting(ShiTing shiting) {
        this.shiting = shiting;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setLocation(Resident location) {
        this.location = location;
    }

    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }
    public int getPriceByInt(){
        return Integer.parseInt(price);
    }
    public Bitmap getPictureByBitmap(){
        Bitmap bit = null;
        try {
            // out = new FileOutputStream("/sdcard/aa.jpg");
            byte[] bytes = Base64.decode(picture, Base64.DEFAULT);
            bit = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            return bit;
        } catch (Exception e) {
            return null;
        }
    }

    public Bitmap getIconByBitmap(){
        Bitmap bit = null;
        try {
            byte[] bytes = Base64.decode(icon, Base64.DEFAULT);
            bit = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            return bit;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int compareTo(BuildingListData build) {
        if(Integer.parseInt(this.getPrice())>Integer.parseInt(build.getPrice())){
            return 1;
        }else if(this.getPrice().equals(build.getPrice())){
            return 0;
        }else{
            return -1;
        }
    }
}
