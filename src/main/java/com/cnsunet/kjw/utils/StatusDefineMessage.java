package com.cnsunet.kjw.utils;

/**
 * @Author: huangjie
 * @Description :API常量值信息获取
 * @Date: Created in 15:48 2018/7/12
 * @Modified By:
 */
public class StatusDefineMessage {

    //获取常量信息
    public static String GetMessage(int s) {
        switch (s) {
            case StatusDefine.SUCCESS:
                return  "操作成功";
            case StatusDefine.FAILURE:
                return "操作失败";
            case StatusDefine.U_INEXISTENCE:
                return  "用户不存在";
            case StatusDefine.U_PWD_FAILED:
                return  "用户名或密码错误";
            case StatusDefine.U_UNCHANGE_PWD:
                return  "用户密码未经修改";
            case StatusDefine.U_UNACTIVE:
                return  "用户未激活";
            case StatusDefine.U_TOKEN_ERROR:
                return  "用户授权码错误";
            case StatusDefine.U_NO_TOKEN:
                return  "用户未授权";
            case StatusDefine.U_ADD_FAILED:
                return  "用户添加失败";
            case StatusDefine.SYS_ERROR:
                return  "系统错误";
            case StatusDefine.NET_ERROR:
                return  "网络请求失败";
            case StatusDefine.DB_ERROR:
                return  "数据库访问失败";
            case StatusDefine.FILE_SERVICE_ERROR:
                return "文件服务器出错";
            case StatusDefine.MSG_SEND_ERROR:
                return "短信发送失败";
            case StatusDefine.READ_EXCEL_ERROR:
                return "读取EXCEL文件失败";
            case StatusDefine.FILE_FORMAT_ERROR:
                return "文件格式错误";
            case StatusDefine.PERMISSIONDENIED:
                return "权限不足";
            case StatusDefine.U_UNLOAD:
                return "用户未登录";
            case StatusDefine.U_LOGIN_OUTTIME:
                return "登录超时,请重新登录";
            case StatusDefine.HAVEPERMISSIOND:
                return "有权限";
            case StatusDefine.REDIS_SERVICE_ERROR:
                return "缓存服务器出错";
            case StatusDefine.ISNULLPARAM:
                return "传入参数为空";

            default:
                return "";
        }
    }
}
