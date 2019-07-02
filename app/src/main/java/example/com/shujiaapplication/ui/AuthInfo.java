package example.com.shujiaapplication.ui;

import java.util.ArrayList;
import java.util.List;

public class AuthInfo {
    public static String userid;
    public static String token;
    private static List<NewBuilding> buildingList111=new ArrayList<>();
    private static List<BuildingListData>buildingList211=new ArrayList<>();
   public static void setAuth(String userid,String token){
       AuthInfo.userid=userid;
       AuthInfo.token=token;
   }
    public static void setBuildingList(List<NewBuilding> a){
       AuthInfo.buildingList111=a;
    }
    public static void setBuildingList2(List<BuildingListData> a){
        AuthInfo.buildingList211=a;
    }
    public static List<NewBuilding> getBuildingList(){
       return buildingList111;
    }
    public static List<BuildingListData> getBuildingList2(){
        return buildingList211;
    }
}
