package example.com.shujiaapplication.ui;

public class AuthInfo {
    public static String userid;
    public static String token;

   public static void setAuth(String userid,String token){
       AuthInfo.userid=userid;
       AuthInfo.token=token;
   }
}
