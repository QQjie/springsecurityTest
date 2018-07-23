package com.cnsunet.kjw.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *@Author  huangjie
 *@Description 数据验证公共类
 *@Date  2018/7/3 9:36
 *@Modyfied by
 */

public class DataValidation {



    /**
     * 校验身份证号码是否有效
     *
     * @author tanshaoxing
     */
    public String CheckCertificateNo(String certificateNo) {
        String retMsg = "";
        if (certificateNo.length() == 15) {
            retMsg = "身份证号码无效，请重新输入！";

        } else if (certificateNo.length() == 18) {
            String address = certificateNo.substring(0, 6);//6位，地区代码
            String birthday = certificateNo.substring(6, 14);//8位，出生日期
            String sequenceCode = certificateNo.substring(14, 17);//3位，顺序码：奇为男，偶为女
            String checkCode = certificateNo.substring(17);//1位，校验码：检验位
            if(!Pattern.matches("^[0-9|x|X]$",checkCode)){
                return retMsg="最后一位为非法字符";
            }
            String[] provinceArray = {"11:北京", "12:天津", "13:河北", "14:山西", "15:内蒙古", "21:辽宁", "22:吉林", "23:黑龙江", "31:上海", "32:江苏", "33:浙江", "34:安徽", "35:福建", "36:江西", "37:山东", "41:河南", "42:湖北 ", "43:湖南", "44:广东", "45:广西", "46:海南", "50:重庆", "51:四川", "52:贵州", "53:云南", "54:西藏 ", "61:陕西", "62:甘肃", "63:青海", "64:宁夏", "65:新疆", "71:台湾", "81:香港", "82:澳门", "91:国外"};
            boolean valideAddress = false;
            for (int i = 0; i < provinceArray.length; i++) {

                String provinceKey = provinceArray[i].split(":")[0];

                if (provinceKey.equals(address.substring(0, 2))) {

                    valideAddress = true;
                }
            }
            if (valideAddress) {
                String year = birthday.substring(0, 4);
                String month = birthday.substring(4, 6);
                String day = birthday.substring(6);
                Date tempDate = new Date(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));

                if ((tempDate.getYear() != Integer.parseInt(year) || tempDate.getMonth() != Integer.parseInt(month) - 1 || tempDate.getDate() != Integer.parseInt(day))) {//避免千年虫问题
                    retMsg = "身份证号码无效，请重新输入！";
                } /*else {
                    int[] weightedFactors = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};//加权因子
                    int[] valideCode = {1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2};//身份证验证位值，其中10代表X
                    int sum = 0;//声明加权求和变量
                    String[] certificateNoArray = new String[certificateNo.length()];
                    for (int i = 0; i < certificateNo.length(); i++) {
                        certificateNoArray[i] = String.valueOf(certificateNo.charAt(i));
                    }
                    if ("x".equals(certificateNoArray[17].toLowerCase())) {
                        certificateNoArray[17] = "10";//将最后位为x的验证码替换为10
                    }
                    for (int i = 0; i < 17; i++) {
                        sum += weightedFactors[i] * Integer.parseInt(certificateNoArray[i]);//加权求和
                    }
                    int valCodePosition = sum % 11;//得到验证码所在位置
                    if (Integer.parseInt(certificateNoArray[17]) == valideCode[valCodePosition]) {
                        String sex = "男";
                        if (Integer.parseInt(sequenceCode) % 2 == 0) {
                            sex = "女";
                        }
                        System.out.println("身份证号码有效，性别为：" + sex + "！");
                    } else {
                        retMsg = "身份证号码无效！";
                    }
                }*/
            } else {
                retMsg = "身份证号码无效！";
            }
        } else {
            retMsg = "非身份证号码,请输入身份证号码！";
        }
        return retMsg;
    }

    //输入手机号码检查是否有误
    public String checkMobile(String mobile) {
        String msg = "";
        String regex = "(\\+\\d+)?1[1-9]\\d{9}$";
        if (mobile.equals(null)) {
            msg = ("手机号码不能为空！");
                /*^匹配开始地方$匹配结束地方，[3|4|5|7|8]选择其中一个{4,8},\d从[0-9]选择
                {4,8}匹配次数4~8    ，java中/表示转义，所以在正则表达式中//匹配/,/匹配""*/
            //验证手机号码格式是否正确
        } else if (!Pattern.matches(regex,mobile)) {
            msg = ("手机号输入有误，请重新输入！");
        }
        return msg;
    }
    //验证非空
    public String checkIsEmpty(String title,String checkData)
    {
        if (StringUtils.isBlank(checkData)){
            return title+"不能为空！";
        }
        return "";
    }
    //验证中文名字
    public  String ChineseName(String name) {
        if (!name.matches("[\u4e00-\u9fa5]{2,6}")) {
            return("只能输入2到6个汉字");
        }else return "";
    }

    /***
     * 验证文件格式
     * @param file 上传文件
     * @param allowTypes 允许上传后缀
     * @return
     */
    public  boolean isValid(MultipartFile file, String... allowTypes) {
        String fileName = file.getOriginalFilename();
        if (null == fileName || "".equals(fileName)) {
            return false;
        }
        for (String type : allowTypes) {
            if (fileName.indexOf(type) > -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证字符长度（最小值和最大值）
     * @param str 验证的字符串
     * @param minLength 最小长度
     * @param maxLength 最大长度
     * @return 成功返回true，失败返回false
     */
    public boolean chkLength(String str,int minLength,int maxLength){
        if (str.length()<minLength){
            return  false;
        }
        if (str.length()>maxLength){
            return false;
        }
        return true;
    }
    /** 整数 */
    private static final String V_INTEGER="^-?[0-9]\\d*$";

    /** 正整数 */
    private static final String V_Z_INDEX="^[1-9]\\d*$";

    /**负整数 */
    private static final String V_NEGATIVE_INTEGER="^-[1-9]\\d*$";

    /** 数字 */
    private static final String V_NUMBER="^([+-]?)\\d*\\.?\\d+$";

    /**正数 */
    private static final String V_POSITIVE_NUMBER="^[1-9]\\d*|0$";

    /** 负数 */
    private static final String V_NEGATINE_NUMBER="^-[1-9]\\d*|0$";

    /** 浮点数 */
    private static final String V_FLOAT="^([+-]?)\\d*\\.\\d+$";

    /** 正浮点数 */
    private static final String V_POSTTIVE_FLOAT="^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";

    /** 负浮点数 */
    private static final String V_NEGATIVE_FLOAT="^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";

    /** 非负浮点数（正浮点数 + 0） */
    private static final String V_UNPOSITIVE_FLOAT="^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$";

    /** 非正浮点数（负浮点数 + 0） */
    private static final String V_UN_NEGATIVE_FLOAT="^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$";

    /** 邮件 */
    private static final String V_EMAIL="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

    /** 颜色 */
    private static final String V_COLOR="^[a-fA-F0-9]{6}$";

    /** url */
    private static final String V_URL="^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";

    /** 仅中文 */
    private static final String V_CHINESE="^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";

    /** 仅ACSII字符 */
    private static final String V_ASCII="^[\\x00-\\xFF]+$";

    /** 邮编 */
    private static final String V_ZIPCODE="^\\d{6}$";

    /** 手机 */
    private static final String V_MOBILE="^(1)[0-9]{10}$";

    /** ip地址 */
    private static final String V_IP4="^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";

    /** 非空 */
    private static final String V_NOTEMPTY="^\\S+$";

    /** 图片 */
    private static final String V_PICTURE="(.*)\\.(jpg|bmp|ico|pcx|jpeg|tif|png|raw|tga)$";

    /** 压缩文件 */
    private static final String V_RAR="(.*)\\.(rar|zip|7zip|tgz)$";

    /** 日期 */
    private static final String V_DATE="^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$";

    /** 带时间的日期 */
    private static final String V_DATETIME="^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";

    /** QQ号码 */
    private static final String V_QQ_NUMBER="^[1-9]*[1-9][0-9]*$";

    /** 电话号码的函数(包括验证国内区号,国际区号,分机号) */
    private static final String V_TEL="^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";

    /** 用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串 */
    private static final String V_USERNAME="^\\w+$";

    /** 字母 */
    private static final String V_LETTER="^[A-Za-z]+$";

    /** 大写字母 */
    private static final String V_LETTER_U="^[A-Z]+$";

    /** 小写字母 */
    private static final String V_LETTER_I="^[a-z]+$";

    /** 身份证 */
    private static final String V_IDCARD ="^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";

    /**验证密码(数字和英文同时存在)*/
    private static final String V_PASSWORD="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";

    /**验证密码长度(6-18位)*/
    private static final String V_PASSWORD_LENGTH="^\\d{6,18}$";

    /**验证两位数*/
    private static final String V_TWO＿POINT="^[0-9]+(.[0-9]{2})?$";

    /**验证一个月的31天*/
    private static final String V_31DAYS="^((0?[1-9])|((1|2)[0-9])|30|31)$";
    /**
     * 验证是不是整数
     * @param value 要验证的字符串 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeInteger(String value){
        return match(V_INTEGER,value);
    }

    /**
     * 验证是不是正整数
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeZ_index(String value){
        return match(V_Z_INDEX,value);
    }

    /**
     * 验证是不是负整数
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeNegative_integer(String value){
        return match(V_NEGATIVE_INTEGER,value);
    }

    /**
     * 验证是不是数字
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeNumber(String value){
        return match(V_NUMBER,value);
    }

    /**
     * 验证是不是正数
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekePositiveNumber(String value){
        return match(V_POSITIVE_NUMBER,value);
    }

    /**
     * 验证是不是负数
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeNegatineNumber(String value){
        return match(V_NEGATINE_NUMBER,value);
    }

    /**
     * 验证一个月的31天
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeIs31Days(String value){
        return match(V_31DAYS,value);
    }

    /**
     * 验证是不是ASCII
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeASCII(String value){
        return match(V_ASCII,value);
    }


    /**
     * 验证是不是中文
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeChinese(String value){
        return match(V_CHINESE,value);
    }



    /**
     * 验证是不是颜色
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeColor(String value){
        return match(V_COLOR,value);
    }



    /**
     * 验证是不是日期
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean checkDate(String value){
        return match(V_DATE,value);
    }

    /**
     * 验证是不是带时间的日期
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean checkDateTime(String value){
        return match(V_DATETIME,value);
    }

    /**
     * 验证是不是邮箱地址
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeEmail(String value){
        return match(V_EMAIL,value);
    }

    /**
     * 验证是不是浮点数
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeFloat(String value){
        return match(V_FLOAT,value);
    }

    /**
     * 验证是不是正确的身份证号码
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeIDcard(String value){
        return match(V_IDCARD,value);
    }

    /**
     * 验证是不是正确的IP地址
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeIP4(String value){
        return match(V_IP4,value);
    }

    /**
     * 验证是不是字母
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeLetter(String value){
        return match(V_LETTER,value);
    }

    /**
     * 验证是不是小写字母
     * @param value 要验证的字符串
     * @return 如果是符合格式灵域的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeLetter_i(String value){
        return match(V_LETTER_I,value);
    }


    /**
     * 验证是不是大写字母
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeLetter_u(String value){
        return match(V_LETTER_U,value);
    }


    /**
     * 验证是不是手机号码
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeMobile(String value){
        return match(V_MOBILE,value);
    }

    /**
     * 验证是不是负浮点数
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeNegative_float(String value){
        return match(V_NEGATIVE_FLOAT,value);
    }

    /**
     * 验证非空
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeNotempty(String value){
        return match(V_NOTEMPTY,value);
    }

    /**
     * 验证密码的长度(6~18位)
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeNumber_length(String value){
        return match(V_PASSWORD_LENGTH,value);
    }

    /**
     * 验证密码(数字和英文同时存在)
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekePassword_reg(String value){
        return match(V_PASSWORD,value);
    }

    /**
     * 验证图片
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekePicture(String value){
        return match(V_PICTURE,value);
    }

    /**
     * 验证正浮点数
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekePosttive_float(String value){
        return match(V_POSTTIVE_FLOAT,value);
    }

    /**
     * 验证QQ号码
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeQQnumber(String value){
        return match(V_QQ_NUMBER,value);
    }

    /**
     * 验证压缩文件
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeRar(String value){
        return match(V_RAR,value);
    }

    /**
     * 验证电话
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeTel(String value){
        return match(V_TEL,value);
    }

    /**
     * 验证两位小数
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeTwo_point(String value){
        return match(V_TWO＿POINT,value);
    }

    /**
     * 验证非正浮点数
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeUn_negative_float(String value){
        return match(V_UN_NEGATIVE_FLOAT,value);
    }

    /**
     * 验证非负浮点数
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeUnpositive_float(String value){
        return match(V_UNPOSITIVE_FLOAT,value);
    }

    /**
     * 验证URL
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeUrl(String value){
        return match(V_URL,value);
    }

    /**
     * 验证用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeUserName(String value){
        return match(V_USERNAME,value);
    }

    /**
     * 验证邮编
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean chekeZipcode(String value){
        return match(V_ZIPCODE,value);
    }

    /**
     * @param regex 正则表达式字符串
     * @param str 要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private  boolean match(String regex, String str)
    {
        if (str==null){
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
