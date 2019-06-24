package example.com.shujiaapplication.model;

import android.graphics.drawable.Drawable;

public class HouseInfo {
    private int house_id;
    private int price;
    private int square;
    private int shi;
    private int ting;
    private String title;
    private String province;
    private String city;
    private String zone;
    private String path;
    String discription;
    private int shortsymbol;
    private int longsymbol;
    private Drawable picture;
    private int living_people;
    public HouseInfo(int house_id,int price,int square,int shi,int ting,String title,String province,String city,String zone,String path,int shortsymbol,int longsymbol,Drawable picture,int living_people){
        setHouse_id(house_id);
        setPrice(price);
        setSquare(square);
        setShi(shi);
        setTing(ting);
        setTitle(title);
        setProvince(province);
        setCity(city);
        setZone(zone);
        setPath(path);
        setShortsymbol(shortsymbol);
        setLongsymbol(longsymbol);
        setPicture(picture);
        setLiving_people(living_people);
    }
    public HouseInfo(Drawable picture,String title,int price,int square,String discription){
        this.picture = picture;
        this.title = title;
        this.price = price;
        this.square = square;
        this.discription = discription;
    }
    public void setHouse_id(int x){
        this.house_id=x;
    }
    public void setPrice(int x){
        this.price=x;
    }
    public void setDiscription(String x){
        this.discription=x;
    }
    public String getDiscription(){
        return this.discription;
    }
    public void setSquare(int x){
        this.square=x;
    }
    public void setShi(int x){
        this.shi=x;
    }
    public void setTing(int x){
        this.ting=x;
    }
    public void setTitle(String x){
        this.title=x;
    }
    public void setProvince(String x){
        this.province=x;
    }
    public void setCity(String x){
        this.city=x;
    }
    public void setZone(String x){
        this.zone=x;
    }
    public void setPath(String x){
        this.path=x;
    }
    public void setShortsymbol(int x){
        this.shortsymbol=x;
    }
    public void setLongsymbol(int x){ this.longsymbol=x;}
    public void setPicture(Drawable x){
        this.picture=x;
    }
    public void setLiving_people(int x){this.living_people=x;}
    public int getHouse_id(){
        return house_id;
    }
    public int getPrice(){
        return price;
    }
    public int getSquare(){
        return square;
    }
    public int getShi(){
        return shi;
    }
    public int getTing(){ return ting;}
    public String getTitle(){
        return title;
    }
    public String getProvince(){
        return province;
    }
    public String getCity(){
        return city;
    }
    public String getZone(){
        return zone;
    }
    public String getPath(){
        return path;
    }
    public int getShortsymbol(){
        return shortsymbol;
    }
    public int getLongsymbol(){
        return longsymbol;
    }
    public Drawable getPicture(){
        return picture;
    }
    public int getLiving_people(){return living_people;}
}
