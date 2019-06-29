package example.com.shujiaapplication.ui;

public class PutComment extends Data{
    private String userid;
    private String token;
    private String houseid;
    private String comment;
    public PutComment(String a,String b,String c,String d){
        setUserid(a);
        setToken(b);
        setHouseid(c);
        setComment(d);
    }
    public void setUserid(String a){this.userid=a;}
    public void setToken(String a){this.token=a;}
    public void setHouseid(String a){this.houseid=a;}
    public void setComment(String a){this.comment=a;}
    public String getUserid(){return userid;}
    public String getToken(){return token;}
    public String getHouseid(){return houseid;}
    public String getComment(){return comment;}
}
