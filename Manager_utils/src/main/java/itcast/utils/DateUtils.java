package itcast.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //日期转换成字符串
    public static String Date2String(Date date ,String patt){
        SimpleDateFormat sd=new SimpleDateFormat(patt);
        String format=sd.format(date);
        return format;
    }
    //字符串转换成日期
    public static Date String2Date(String date,String patt){
        SimpleDateFormat sd=new SimpleDateFormat(patt);
        try {
            Date parse=sd.parse(date);
            return parse;
        } catch (ParseException e) {
            throw new RuntimeException("转换错误");
        }
    }
}
