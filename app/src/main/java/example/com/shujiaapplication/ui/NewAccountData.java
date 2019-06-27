package example.com.shujiaapplication.ui;

import com.google.gson.annotations.SerializedName;

public class NewAccountData extends Data{
    @SerializedName("phonenum")
    private String newAccountName;
    @SerializedName("password")
    private String newAccountPassword;
    @SerializedName("code")
    private String code;
    @SerializedName("time")
    private String time;

    public NewAccountData(String ph,String c,String t,String p){
        this.newAccountName = ph;
        this.code = c;
        this.time = t;
        this.newAccountPassword = p;

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getNewAccountName() {
        return newAccountName;
    }

    public String getNewAccountPassword() {
        return newAccountPassword;
    }

    public void setNewAccountName(String newAccountName) {
        this.newAccountName = newAccountName;
    }

    public void setNewAccountPassword(String newAccountPassword) {
        this.newAccountPassword = newAccountPassword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
