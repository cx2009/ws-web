package com.jsxk.ws.config;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

public class AlipayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner = "2088...";
    // 商户的私钥
    public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKXcJq3Aj7uwht...";
    public static String seller_email = "2088...";

    // 支付宝的公钥
    public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89...";


    // 调试用，创建TXT日志文件夹路径
    public static String log_path = "D:\\";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";

    // 签名方式
    public static String sign_type = "RSA";

    public static String APPID;

    public static String PRIVATE_KEY;


    public static String PUBLIC_KEY;


    public static  String NotifyUrl="";


}
