package example.com.shujiaapplication.ui;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
public class Pay extends Data implements Serializable {
    @SerializedName("alipay")
    private String alipay;
    @SerializedName("wechatpay")
    private String wechatpay;
    @SerializedName("balance")
    private String balance;
    public void setAlipay(String a){this.alipay=a;}
    public void setWechatpaypay(String a){this.wechatpay=a;}
    public void setBalance(String a){this.balance=a;}
    public String getAlipay(){return alipay;}
    public String getWechatpayipay(){return wechatpay;}
    public String getBalance(){return balance;}
}
