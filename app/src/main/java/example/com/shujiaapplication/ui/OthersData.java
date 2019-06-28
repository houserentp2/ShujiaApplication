package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class OthersData extends Data {
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
