package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PayResult extends Data implements Serializable {
    @SerializedName("userid")
    private String userid;
    @SerializedName("payresult")
    private String payresult;
    public void setUserid(String a){
        this.userid=a;
    }
    public void setPayresult(String a){
        this.payresult=a;
    }
    public String getUserid(){
        return  userid;
    }
    public String getPayresult(){
        return payresult;
    }
}
