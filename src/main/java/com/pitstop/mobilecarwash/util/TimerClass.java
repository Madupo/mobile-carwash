package com.pitstop.mobilecarwash.util;

/**
 * Created by Emmie on 8/15/2017.
 */
import com.pitstop.mobilecarwash.entity.WashTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimerClass{
    public static void main(String[] args)throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String dateInString = "08:00:00";
        Date date = sdf.parse(dateInString);
        System.out.println("Date is " + date);

        List<WashTime> washTimes=new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long eight = calendar.getTimeInMillis();
        System.out.println("Calender - Time in milliseconds : " + eight );






        WashTime washTime = new WashTime("08:00 to 10:00");
        WashTime washTime1 = new WashTime("10:00 to 12:00");
        WashTime washTime2 = new WashTime("13:00 to 15:00");
        WashTime washTime3 = new WashTime("15:00 to 17:00");
        WashTime washTime4 = new WashTime("17:00 to 18:00");

        washTimes.add(washTime);
        washTimes.add(washTime1);
        washTimes.add(washTime2);
        washTimes.add(washTime3);
        washTimes.add(washTime4);

        for(WashTime objWashTime: washTimes){

        }



    }
}

