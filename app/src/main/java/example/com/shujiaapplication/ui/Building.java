package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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

//    public Building(String house_id, String price, String square, String shi, String ting, String title,
//                    String province, String city, String zone, String path, String shortsymbol,
//                    String longsymbol, List<Integer> picture_id, String build_head, String collect_image,
//                    String living_people){
public Building(String userid, String token, String houseid,String time, String price, String square, String shi, String ting,
                String title,String description, String province, String city, String zone, String path,
                String[] pictures){
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

    public class Resident{
        @SerializedName("province")
        private String province;

        @SerializedName("city")
        private String city;

        @SerializedName("zone")
        private String zone;

        @SerializedName("path")
        private String path;

        public Resident(String province,String city,String zone,String path){
            this.province = province;
            this.city = city;
            this.zone = zone;
            this.path = path;
        }

        public String getCity() {
            return city;
        }

        public String getPath() {
            return path;
        }

        public String getProvince() {
            return province;
        }

        public String getZone() {
            return zone;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setZone(String zone) {
            this.zone = zone;
        }
    }

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
