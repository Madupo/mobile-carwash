package com.pitstop.mobilecarwash.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by emmie on 2017/07/12.
 */
public class TextUtil {
    public static void main(String[] args) {
        String smsText= "Please be advised that @ %s the ticket failures for %s have reached the threshold of 120. Current count failures is: %s";
        //String smsText= "Thank you for booking on flysaa.com. Your ticket number/s for SAA reference number : %s is %s. See you on board!";
        Date date = new Date();
        String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String time = new SimpleDateFormat("hh:mm:ss").format(date);
        System.out.println("**Un-Formatted sms text: "+smsText);
        smsText = String.format(smsText, time, newstring, 120);
        System.out.println("**Formatted sms text: "+smsText);
    }
}

