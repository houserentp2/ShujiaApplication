package example.com.shujiaapplication.ui;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class NewBuilding extends Data implements Serializable {
    @SerializedName("userid")
    private String userid;
    @SerializedName("token")
    private String token;
    @SerializedName("houseid")
    private String houseid;
    @SerializedName("hostid")
    private String hostid;
    @SerializedName("orderid")
    private String orderid;
    @SerializedName("discountid")
    private String discountid;
    @SerializedName("pay")
    private Pay pay;
    @SerializedName("time")
    private String time;
    @SerializedName("start")
    private String start;
    @SerializedName("stop")
    private String stop;
    @SerializedName("result")
    private int result;

    public NewBuilding(String id,String t,String hid,String hoid,String oid,String did,String tme,String s,String op,int res,Pay p){
        setDiscountid(did);
        setHostid(hoid);
        setHouseidid(hid);
        setOrderid(oid);
        setPay(p);
        setResult(res);
        setStart(s);
        setStop(op);
        setTime(tme);
        setToken(t);
        setUserid(id);
    }

    public void setUserid(String a){this.userid=a;}
    public void setToken(String a){this.token=a;}
    public void setHouseidid(String a){this.houseid=a;}
    public void setHostid(String a){this.hostid=a;}
    public void setOrderid(String a){this.orderid=a;}
    public void setDiscountid(String a){this.discountid=a;}
    public void setTime(String a){this.time=a;}
    public void setStart(String a){this.start=a;}
    public void setStop(String a){this.stop=a;}
    public void setResult(int a){this.result=a;}
    public void setPay(Pay a){this.pay=a;}
    public String getUserid(){return userid;}
    public String getToken(){return token;}
    public String getHouseid(){return houseid;}
    public String getHostid(){return hostid;}
    public String getOrderidid(){return orderid;}
    public String getDiscountid(){return discountid;}
    public String getTime(){return time;}
    public String getStart(){return start;}
    public String getStop(){return stop;}
    public int getResult(){return result;}
    public Pay pay(){return pay;}
}
