package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class Resident{
    @SerializedName("province")
    private String province;

    @SerializedName("city")
    private String city;

    @SerializedName("zone")
    private String zone;

    @SerializedName("path")
    private String path;

    public Resident(String p,String c,String z,String pa){
        this.province = p;
        this.city = c;
        this.zone = z;
        this.path = pa;
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