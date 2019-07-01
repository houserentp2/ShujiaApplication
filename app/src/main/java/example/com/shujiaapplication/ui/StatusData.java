package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class StatusData extends Data {
    @SerializedName("living")
    private int living;
    @SerializedName("tolive")
    private int tolive;
    @SerializedName("lived")
    private int lived;

    StatusData(int living, int tolive, int lived){
        setLived(lived);
        setLiving(living);
        setTolive(tolive);
    }

    public void setLived(int lived) {
        this.lived = lived;
    }

    public void setLiving(int living) {
        this.living = living;
    }

    public void setTolive(int tolive) {
        this.tolive = tolive;
    }

    public int getLived() {
        return lived;
    }

    public int getLiving() {
        return living;
    }

    public int getTolive() {
        return tolive;
    }
}
