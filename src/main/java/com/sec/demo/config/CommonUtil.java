package com.sec.demo.config;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

public class CommonUtil {

    public static String readCSV(String path) {
        String charset = "utf-8";
        String res = "";
        try (CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), charset))).build()) {
            Iterator<String[]> iterator = csvReader.iterator();
            while (iterator.hasNext()) {
//                Arrays.stream(iterator.next()).forEach(e -> System.out.print(e));
                for(Object e : Arrays.stream(iterator.next()).toArray()){
                    res = res + e + "\t";
                }
            }
            res = res.trim();
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String timedel(Date time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(time);
        return dateString;
    }

    public static Date timeadd(String time){
        String year = time.substring(0,4);
        String month = time.substring(4,6);
        String day = time.substring(6,8);
        String hour = time.substring(8,10);
        String minute = time.substring(10,12);
        String second = time.substring(12,14);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second, pos);
        return strtodate;
    }

    public static Date randomDate(String beginDate,String endDate){
        try{
            //时间格式化
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //定义开始时间
            Date start = format.parse(beginDate);
            //定义结束时间
            Date end = format.parse(endDate);
            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            return new Date(date);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static long random(long begin,long end){

        long rtn = begin + (long)(Math.random() * (end - begin));
        //如果返回的是开始时间和结束时间，通过递归调用本函数查找随机值
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }
}

