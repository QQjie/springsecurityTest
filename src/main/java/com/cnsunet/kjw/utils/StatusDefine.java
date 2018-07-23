package com.cnsunet.kjw.utils;

/**
 *@Author  huangjie
 *@Description 常量接口定义API接口返回值
 *@Date  2018/7/3 9:36
 *@Modyfied by
 */

public interface StatusDefine {
    //操作成功
    int SUCCESS = 0;
    int FAILURE=-1;

    //系统状态
    //系统错误
    int SYS_ERROR = 5001;
    //网络请求失败
    int NET_ERROR = 5002;
    //数据库访问失败
    int DB_ERROR = 5003;
    //文件服务器出错
    int FILE_SERVICE_ERROR=5004;
    //缓存redis服务器出错
    int REDIS_SERVICE_ERROR = 5006;
    //短信发送失败
    int MSG_SEND_ERROR=5007;
    //读取Excel文件失败
    int READ_EXCEL_ERROR=5008;
    //数据合法化验证
    //数据格式不正确
    int DATA_FORMAT_ERROR=1001;
    //文件格式不正确
    int FILE_FORMAT_ERROR=1002;

    //传入参数为空
    int ISNULLPARAM = 501;

    //用户相关
    //用户不存在d
    int U_INEXISTENCE = 2001;
    //用户密码错误
    int U_PWD_FAILED = 2002;
    //用户密码未经修改
    int U_UNCHANGE_PWD = 2003;
    //用户未激活
    int U_UNACTIVE = 2004;
    //用户未登录
    int U_UNLOAD =2009;
    //用户授权码错误
    int U_TOKEN_ERROR = 2005;
    //用户未授权
    int U_NO_TOKEN = 2006;
    //用户已经存在
    int U_EXIST_USER = 2007;
    //添加用户失败
    int U_ADD_FAILED = 2008;


    //登录超时
    int U_LOGIN_OUTTIME =3000;




    //权限不足
    int PERMISSIONDENIED = 8001;
    //有权限
    int HAVEPERMISSIOND=8002;


    //角色名不存在
    int R_INEXISTENCE = 7001;
    //角色名已经存在
    int R_EXIST_USER = 7002;
}
