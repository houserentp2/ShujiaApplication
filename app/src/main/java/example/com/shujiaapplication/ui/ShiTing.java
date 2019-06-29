package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;
public class ShiTing extends Data{
    @SerializedName("shi")
    private int shi;

    @SerializedName("ting")
    private int ting;

    public ShiTing(int a,int b){
        setShi(a);
        setTing(b);
    }

    public int getShi() {
        return shi;
    }
    public void setShi(int s){
        shi = s;
    }

    public int getTing() {
        return ting;
    }

    public void setTing(int ting) {
        this.ting = ting;
    }
}
