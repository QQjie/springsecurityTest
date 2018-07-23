package com.cnsunet.kjw.utils;


import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 系统工具类
 * Create by huangjie on 2018-07-03.
 */
public class Utils {
    //======================================== 基础工具 ========================================

    /**
     * 根据时间戳返回格式字符串
     */
   /* public static String currentDateTime(int delaySeconds) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, delaySeconds);
        return Config.DATE_FORMAT.format(now.getTime());
    }*/

    /**
     * 根据时间戳和格式参数返回格式字符串
     */
    public static String currentDateTime(int delaySeconds, String formatStr) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, delaySeconds);
        DateFormat DATE_FORMAT = new SimpleDateFormat(formatStr, Locale.CHINA);
        return DATE_FORMAT.format(now.getTime());
    }

    /**
     * MD5转码
     */
    public static String getMd5DigestAsHex(String input) {
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }


    /**
     * Escape " for shell command.
     */
    public static String escapeShell(String s) {
        return s.replace("\"", "\\\"");
    }


    /**
     * 将时间格式字符串按格式转换成日期
     * add by yanggen 20170818
     */
    public static Date strToDate(String strDate, DateFormat formatter) {
        Date strtodate = null;
        try {
            strtodate = formatter.parse(strDate);
        } catch (ParseException e) {
            strtodate = null;
        }
        return strtodate;
    }

    /**
     * 将时间按格式转换成字符串
     * add by yanggen 20170818
     */
    public static String dateToStr(Date date, DateFormat formatter) {
        String dateStr;
        try {
            dateStr = formatter.format(date);
        } catch (Exception e) {
            dateStr = null;
        }
        return dateStr;
    }

    public static Date getCurrDateBefore(int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    //删除字符末尾零
    public static String delLastZero(String str) {
        if (StringUtils.isBlank(str)) return null;
        String tempStr = str;
        if (str.length() - 1 == str.lastIndexOf("0")) {
            tempStr = str.substring(0, str.length() - 1);
            tempStr = delLastZero(tempStr);
        }
        return tempStr;
    }

    /**
     * 产生4位随机数(0000-9999)
     * create by zhgp
     * @return 4位随机数
     */
    public static String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++)
                fourRandom = "0" + fourRandom  ;
        }
        return fourRandom;
    }

    /**
     * 图片转换成base64 字符串
     * @param bufferedImage
     * @return
     */
    public static String encodeImgageToBase64( BufferedImage bufferedImage) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
//        // 对字节数组Base64编码
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
    }
    //加密
    public static String getBase64(String str){
        byte[] b=null;
        String s=null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(b!=null){
            s=new BASE64Encoder().encode(b);
        }
        return s;
    }
    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //去除时间末尾0
    public static String DelTimeO(String time){
        if(!StringUtils.isBlank(time)) {
            time = time.substring(0, time.length() - 2);
        }
        return time;
    }

    /**
     * 获取当前时间
     * @return 返回当前时间字符串
     */
    public String getCurrentTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date()).toString();
    }
    /**
     * 对比数组公共方法
     * 方法名：fillModifyDetails
     * 方法返回类型：返回2个数组中不相同的元素
     */
    public static <T> List<T> compare(T[] t1, T[] t2) {
        List<T> list1 = Arrays.asList(t1);
        List<T> list2 = new ArrayList<T>();
        for (T t : t2) {
            if (!list1.contains(t)) {
                list2.add(t);
            }
        }
        return list2;
    }
    /**
     * 根据数据库返回的0和1返回是和否
     * 方法名：CheckStringByInt
     * 方法返回类型：返回是活否
     */
    public static String CheckStringByInt(Integer num){
        if(num==0){
            return "否";
        }else if(num==1){
            return "是";
        }
        return "";
    }
}