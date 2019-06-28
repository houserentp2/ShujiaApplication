package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Building implements  Comparable<Building>, Serializable {

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


    public Building(String userid, String token, String houseid,String time, String price, String square,
                    String shi, String ting, String title,String description,
                    String province, String city, String zone, String path, String[] pictures){
        setUserid(userid);
        setToken(token);
        setHouseid(houseid);
        setTime(time);
        setPrice(price);
        setSquare(square);
        this.shiting = new ShiTing(shi,ting);
        setTitle(title);
        setDescription(description);
        this.location = new Resident(province, city, zone, path);
        setPictures(pictures);
    }

    ///////////////////////////////////////////////////////////////////
    private int build_head;
    private int house_id;
    private int shi;
    private int ting;
    private String province;
    private String city;
    private String zone;
    private String path;
    private int shortsymbol;
    private int longsymbol;
    private List<Integer> picture_id;
    private int living_people;
    private int collect_image;
    private ArrayList<String> buildingView=new ArrayList<String>();
    public Building(int house_id,int price,int square,int shi,int ting,String title,String province,String city,String zone,String path,int shortsymbol,int longsymbol,List<Integer> picture_id,int build_head,int collect_image,int living_people){
        setHouse_id(house_id);
        setPrice(String.valueOf(price));
        setSquare(String.valueOf(square));
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
        setBuild_head(build_head);
        setCollect_image(collect_image);
        setLiving_people(living_people);
    }

    public int getCollect_image() {
        return collect_image;
    }

    public void setCollect_image(int collect_image) {
        this.collect_image = collect_image;
    }

    public int getBuild_head() {
        return build_head;
    }

    public void setBuild_head(int build_head) {
        this.build_head = build_head;
    }

    public void setHouse_id(int x){
        this.house_id=x;
    }
    public void setShi(int x){
        this.shi=x;
    }
    public void setTing(int x){
        this.ting=x;
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
    public void setPicture_id(List<Integer> x){
        this.picture_id=x;
    }
    public void setLiving_people(int x){this.living_people=x;}
    public void setBuildingView(String a){
        buildingView.add(a);
    }
    public int getHouse_id(){
        return house_id;
    }
    public int getShi(){
        return shi;
    }
    public int getTing(){ return ting;}

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
    public List<Integer> getPicture_id(){
        return picture_id;
    }
    public int getLiving_people(){return living_people;}
    public ArrayList<String> getBuildingView(){return buildingView;}

    ///////////////////////////////////////////////////////////////////



    public class OthersData {
        @SerializedName("water")
        private String water;
        @SerializedName("power")
        private String power;
        @SerializedName("net")
        private String net;
        @SerializedName("hot")
        private String hot;
        @SerializedName("aircon")
        private String aircon;
        @SerializedName("bus")
        private String bus;
        @SerializedName("capacity")
        private String capacity;
        @SerializedName("comments")
        private String[] comments;
        @SerializedName("status")
        private StatusData status;

        OthersData(String water, String power, String net, String hot, String aircon, String bus,
                   String capacity, String[]comments,String living, String tolive, String lived ){
            setWater(water);
            setPower(power);
            setNet(net);
            setHot(hot);
            setAircon(aircon);
            setBus(bus);
            setCapacity(capacity);
            setComments(comments);
            this.status = new StatusData(living, tolive, lived);
        }


        public class StatusData {
            @SerializedName("living")
            private String living;
            @SerializedName("tolive")
            private String tolive;
            @SerializedName("lived")
            private String lived;

            StatusData(String living, String tolive, String lived){
                setLiving(living);
                setTolive(tolive);
                setLived(lived);
            }

            public void setLived(String lived) {
                this.lived = lived;
            }

            public void setLiving(String living) {
                this.living = living;
            }

            public void setTolive(String tolive) {
                this.tolive = tolive;
            }

            public String getLived() {
                return lived;
            }

            public String getLiving() {
                return living;
            }

            public String getTolive() {
                return tolive;
            }
        }



        public String getCapacity() {
            return capacity;
        }

        public StatusData getStatus() {
            return status;
        }

        public String[] getComments() {
            return comments;
        }

        public void setCapacity(String capacity) {
            this.capacity = capacity;
        }

        public void setComments(String[] comments) {
            this.comments = comments;
        }

        public void setStatus(StatusData status) {
            this.status = status;
        }

        public String getAircon() {
            return aircon;
        }

        public String getBus() {
            return bus;
        }

        public String getHot() {
            return hot;
        }

        public String getNet() {
            return net;
        }

        public String getPower() {
            return power;
        }

        public String getWater() {
            return water;
        }

        public void setAircon(String aircon) {
            this.aircon = aircon;
        }

        public void setBus(String bus) {
            this.bus = bus;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public void setNet(String net) {
            this.net = net;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public void setWater(String water) {
            this.water = water;
        }
    }


    public class ShiTing {
        @SerializedName("shi")
        private String shi;

        @SerializedName("ting")
        private String ting;

        ShiTing(String shi, String ting){
            setShi(shi);
            setTing(ting);
        }

        public String getShi() {
            return shi;
        }
        public void setShi(String s){
            shi = s;
        }

        public String getTing() {
            return ting;
        }

        public void setTing(String ting) {
            this.ting = ting;
        }
    }

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

    public void setShiting(String Shi, String Ting) {
        this.shiting.setShi(Shi);
        this.shiting.setTing(Ting);
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

    public int compareTo(Building o) {
        if(Integer.parseInt(this.getPrice())>Integer.parseInt(o.getPrice())){
            return 1;
        }else if(this.getPrice().equals(o.getPrice())){
            return 0;
        }else{
            return -1;
        }
    }
}
