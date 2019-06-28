package example.com.shujiaapplication.ui.myDatePicker;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCalculate {
    public static int calculateDays(String inDate,String outDate) throws ParseException {
        return ((int) ( myGetTime(inDate,outDate) / (1000 * 60 * 60 *24)));
    }

    public static int calculateMonths(String inDate,String outDate) throws ParseException {
        return (int) ( myGetTime(inDate,outDate) / (1000 * 60 * 60 *24 * 30))+1;

    }

    public static long myGetTime(String inDate,String outDate) throws ParseException {
        SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
        Date dateIn= sdfIn.parse(inDate);
        Date dateOut= sdfIn.parse(outDate);
        long beginTime = dateIn.getTime();
        long endTime = dateOut.getTime();
        return endTime - beginTime;
    }
}
