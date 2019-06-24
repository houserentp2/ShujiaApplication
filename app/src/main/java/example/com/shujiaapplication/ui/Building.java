package example.com.shujiaapplication.ui;

public class Building {
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
    private int shortsymbol;
    private int longsymbol;
    private int picture_id;
    private int living_people;
    public Building(int house_id,int price,int square,int shi,int ting,String title,String province,String city,String zone,String path,int shortsymbol,int longsymbol,int picture_id,int living_people){
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
        setPicture_id(picture_id);
        setLiving_people(living_people);
    }
    public void setHouse_id(int x){
        this.house_id=x;
    }
    public void setPrice(int x){
        this.price=x;
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
    public void setPicture_id(int x){
        this.picture_id=x;
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
    public int getPicture_id(){
        return picture_id;
    }
    public int getLiving_people(){return living_people;}
}
