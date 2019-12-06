package com.qingyun.controller.client;

import com.handongkeji.config.auth.AuthRuleAnnotation;
import com.handongkeji.tencent.common.report.protocol.UniCallbackReqData;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.dao.MbOrderMapper;
import com.qingyun.mybatis.model.MbOrder;
import com.qingyun.mybatis.model.MbOrderExample;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import com.qingyun.weixinpay.AppPayReqData;
import com.qingyun.weixinpay.UniCallbackResData;
import com.qingyun.weixinpay.config.WexinPayConfig;
import com.qingyun.weixinpay.util.HttpsRequest;
import com.qingyun.weixinpay.util.SignUtil;
import com.qingyun.weixinpay.util.WeiXinPayRefund;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Captain Ren
 * @Description TODO
 * @Date 2019/4/17 0017 17:09
 * @Param
 * @return
 **/
@Slf4j
@RestController
@RequestMapping("wxpay")
public class WeiXinPayController{

    @Autowired
    MbOrderMapper mbOrderMapper;
    @RequestMapping("/QRCodePay")
    public ResultVo QRCodePay( String subject)
    {
        //生成
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String orderNo =  sdf.format(new Date()) + (1 + (int) (Math.random() * 10000));
        MbOrder mbOrder= null;
        try {
            mbOrder = new MbOrder();
            mbOrder.setOrdernumber(orderNo);
            mbOrder.setPaymenttype(0);
            mbOrder.setCreatetime(new Date());
            mbOrderMapper.insertSelective(mbOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.falure("创建订单失败！");
        }
        AppPayReqData postdata = new AppPayReqData(subject, mbOrder.getOrdernumber(), 500, "", "NATIVE");
        log.info("提交的微信支付的下单参数：\n" + postdata.toString());
        UniCallbackResData rsModel = qrPlaceOrder(postdata);
        log.info("微信支付的下单返回的数据：\n" + rsModel == null ? "null" : rsModel.toString());
        if (rsModel != null && "SUCCESS".equals(rsModel.getReturn_code()))
        {
            if ("SUCCESS".equals(rsModel.getResult_code()))
            {
                Map<String,Object> map=new HashMap<>();
                map.put("orderid",mbOrder.getOrderid());
                map.put("codeurl",rsModel.getCode_url());
                return ResultVOUtils.success(map);
            }
            else
            {
                return ResultVOUtils.falure(rsModel.getReturn_msg());
            }
        }
        else
        {
            return ResultVOUtils.falure(rsModel.getReturn_msg());
        }
    }

    public UniCallbackResData qrPlaceOrder(AppPayReqData postdata)
    {

        String url = WexinPayConfig.unified_order_api;
        UniCallbackResData rsModel = null;
        try
        {
            HttpsRequest hsq = null;
            try
            {
                hsq = new HttpsRequest();
            }
            catch (Exception e)
            {
                log.info("二维码支付统一下单异常：" + e.getMessage());
                e.printStackTrace();
            }
            String result = hsq.sendPost(url, postdata);
            rsModel = (UniCallbackResData) WeiXinPayRefund.getObjectFromXML(result, UniCallbackResData.class);
        }
        catch (UnrecoverableKeyException | KeyManagementException | NoSuchAlgorithmException | KeyStoreException | IOException e)
        {
            log.info("二维码支付微信支付下单出错！：" + e.getMessage());
            return null;
        }
        catch (Exception e)
        {
            log.info("二维码支付微信支付下单出错：" + e.getMessage());
            return null;
        }
        return rsModel;
    }


    /**
     * 查询微信支付状态
     */
    @GetMapping("getWxPayType")
    public ResultVo getWxPayType(Long orderid) {
        //参数验证
        if (orderid==null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数异常");
        }
        try {
            MbOrder mbOrder = mbOrderMapper.selectByPrimaryKey(orderid);
            ResultVo resultVo = new ResultVo();
            resultVo.setCode(0);
            resultVo.setData(mbOrder);
            return resultVo;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }



    @RequestMapping("/notifyWxpay")
    public void notifyWxpay(HttpServletRequest request, HttpServletResponse response) {
        log.info("============微信支付通知notifyWxpay===============");
        response.setContentType("text/html;charset=utf-8");
        //回调返回值
        String responseXML = "";
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1)
            {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
            UniCallbackReqData reqData = (UniCallbackReqData) SignUtil.getObjectFromXML(result, UniCallbackReqData.class);
            if (reqData != null)
            {
                String return_code = reqData.getReturn_code();
                // 商户订单号
                String out_trade_no = reqData.getOut_trade_no();
                // 微信交易订单号
                String transaction_id = reqData.getTransaction_id();
                // 商家附加参数数据，微信会原样返回
                String userid = reqData.getAttach();  //用户ID
                // 金额
                String total_fee = "0";
                if ("SUCCESS".equals(return_code) && SignUtil.isTenpaySign(SignUtil.obj2Map(reqData), reqData.getSign()))
                {
                    log.info("=========微信支付成功===========");
                    //回调业务逻辑处理
                    // 业务处理
                    synchronized (this) {
                        log.info("系统订单号：" + out_trade_no);
                        // 参数校验
                        String mixstr = transaction_id;
                        Long orderid = null;
                        //String[] mixarr = null;
                        try {
                            if (mixstr == null) {
                                throw new Exception();
                            }
                            //mixarr = mixstr.split("@");
                            //order_id = Long.parseLong(mixarr[0]);
                        } catch (Exception e1) {
                            // 支付失败 ,直接返回
                            e1.printStackTrace();
                        }
                        MbOrderExample example=new MbOrderExample();
                        example.createCriteria().andOrdernumberEqualTo(out_trade_no);
                        List<MbOrder> mbOrders=mbOrderMapper.selectByExample(example);
                        if(mbOrders.size()>0){
                            MbOrder mbOrder=mbOrders.get(0);
                            mbOrder.setPaymenttype(1);
                            mbOrderMapper.updateByPrimaryKeySelective(mbOrder);
                        }

                    }
                    //回写回调成功标识
                    responseXML = SignUtil.setXML("SUCCESS", "");
                }
                else
                {
                    responseXML = SignUtil.setXML("FAIL", "");
                    log.info("微信支付通知失败====回调验签失败######");
                }
            }
            else
            {
                responseXML = SignUtil.setXML("FAIL", "");
                log.info("微信支付通知失败====无返回数据######");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.info("微信支付回调失败：" + e.getMessage());
        }
        if(out != null)
        {
            out.print(responseXML);
            out.flush();
            out.close();
            out = null;
        }
    }
}
