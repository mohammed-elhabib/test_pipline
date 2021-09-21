package dz.itinfinity.orderMS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

public class testing {




    public static void main(String[] args){
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("fr", "FR"));
        String date = simpleDateFormat.format(new Date());
        System.out.println();

        LocalTime myObj = LocalTime.now(); // Create a date object
        System.out.println(myObj);
        
        LocalDateTime myObjf = LocalDateTime.now();
        System.out.println(myObjf);
    }

}
