package example.com.shujiaapplication.ui;

public class NewAccountData {
    private String newAccountName;
    private String newAccountPassword;
    private String code;

    public NewAccountData(String ph,String p,String c){
        this.newAccountName = ph;
        this.newAccountPassword = p;
        this.code = c;
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
