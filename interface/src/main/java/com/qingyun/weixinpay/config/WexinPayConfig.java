package com.qingyun.weixinpay.config;

/**
 * @Author Captain Ren
 * @Description TODO
 * @Date 2019/4/16 0016 15:35
 * @Param
 * @return
 **/
public class WexinPayConfig
{
    //开放平台 商户号
    public static String wx_mchid = "1555010511";
    //开放平台 appid
    public static String wx_appid = "wx6efe7ee000e28d5f";
    //商户平台apikey  32位秘钥
    public static String  wx_apikey = "88888888888888888888888888888888";
    //微信退款api接口
    public static String wx_refund_url = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    //微信回调url
    public static String  wx_notify = "http://47.105.204.10:8886/wxpay/notifyWxpay";
    //统一下单API
    public static String unified_order_api = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
