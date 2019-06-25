package example.com.shujiaapplication.ui;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static example.com.shujiaapplication.ui.MyApplication.getContext;

public class CityDataGenerator {
    private InputStream is;
    private BufferedReader br;
    private ArrayList<String> cities;

    public CityDataGenerator(){
        this.readFile("city");
    }

    public  void readFile(String fileName) {                                                          //将站点信息存入数据库
        String temp = "";
        String tureFileName = fileName + ".txt";
        cities = new ArrayList<String>();
        try {
            is = MyApplication.getContext().getAssets().open(tureFileName);
            br = new BufferedReader(new InputStreamReader(is));
            while((temp = br.readLine()) != null){
                cities.add(temp);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public ArrayList<String> getList(String s){
        ArrayList<String> ans = new ArrayList<String>();
        for(String str:cities){
            if(str.contains(s)){
                ans.add(str);
            }
        }

        return ans;
    }

    public Boolean findCity(String s) throws IOException {
        if(cities.contains(s)){
            return true;
        }
        return false;
    }

}
