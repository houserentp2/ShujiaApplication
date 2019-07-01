package example.com.shujiaapplication.ui;

import java.io.Serializable;
import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;
public class Pay extends Data implements Serializable {
    @SerializedName("alipay")
    private BigDecimal alipay;
    @SerializedName("wechatpay")
    private BigDecimal wechatpay;
    @SerializedName("balance")
    private BigDecimal balance;
    public void setAlipay(BigDecimal a){this.alipay=a;}
    public void setWechatpaypay(BigDecimal a){this.wechatpay=a;}
    public void setBalance(BigDecimal a){this.balance=a;}
    public BigDecimal getAlipay(){return alipay;}
    public BigDecimal getWechatpayipay(){return wechatpay;}
    public BigDecimal getBalance(){return balance;}
}
