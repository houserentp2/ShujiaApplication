package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class OthersData extends Data {
    @SerializedName("water")
    private int water;
    @SerializedName("power")
    private int power;
    @SerializedName("net")
    private int net;
    @SerializedName("hot")
    private int hot;
    @SerializedName("aircon")
    private int aircon;
    @SerializedName("bus")
    private int bus;
    @SerializedName("short")
    private int shortx;
    @SerializedName("long")
    private int longx;
    @SerializedName("capacity")
    private int capacity;
    @SerializedName("comments")
    private CommentsData[] comments;
    @SerializedName("status")
    private StatusData status;

    OthersData(int water, int power, int net, int hot, int aircon, int bus, int shortx,int longx,
               int capacity,CommentsData[] comments,int living, int tolive, int lived){
        setWater(water);
        setPower(power);
        setNet(net);
        setHot(hot);
        setAircon(aircon);
        setBus(bus);
        setShortx(shortx);
        setLongxx(longx);
        setCapacity(capacity);
        setComments(comments);
        status = new StatusData(living,tolive,lived);
    }
//    OthersData(String water, String power, String net, String hot, String aircon, String bus, int shortx,int longx,
//               String capacity,String []comments){
//        setWater(water);
//        setPower(power);
//        setNet(net);
//        setHot(hot);
//        setAircon(aircon);
//        setBus(bus);
//        setShortx(shortx);
//        setLongxx(longx);
//        setCapacity(capacity);
//        setComments(comments);
//    }

    public int getCapacity() {
        return capacity;
    }

    public int getShortx(){return shortx;}

    public int getLongx(){return longx;}


    public StatusData getStatus() {
        return status;
    }

    public CommentsData[] getComments() {
        return comments;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setComments(CommentsData[] comments) {
        this.comments = comments;
    }

    public void setStatus(StatusData status) {
        this.status = status;
    }

    public int getAircon() {
        return aircon;
    }

    public int getBus() {
        return bus;
    }

    public int getHot() {
        return hot;
    }

    public int getNet() {
        return net;
    }

    public int getPower() {
        return power;
    }

    public int getWater() {
        return water;
    }

    public void setAircon(int aircon) {
        this.aircon = aircon;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public void setShortx(int a){this.shortx=a;}

    public void setLongxx(int a){this.longx=a;}
}
