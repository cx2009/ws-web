package com.jsxk.ws.controller.App;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jsxk.ws.common.AuthManager;
import com.jsxk.ws.common.errorcode.ErrorCodes;
import com.jsxk.ws.config.AlipayConfig;
import com.jsxk.ws.model.Bo.PayQuery;
import com.jsxk.ws.model.Order;
import com.jsxk.ws.service.OrderService;
import com.jsxk.ws.utils.ControllerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/order")
public class UserOrderController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    OrderService orderService;


    @Autowired
    AuthManager authManager;


    @RequestMapping(value = "/creatOrder", method = RequestMethod.POST)
    public String createOrder(@RequestBody @Valid PayQuery payQuery, HttpServletRequest request) {
        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        String out_trade_no = orderService.CreateOrederId(authManager.getUserInfoByToken(request).getUserId());

        Order order = new Order();

        order.setAmount(payQuery.getAmount());

        order.setOrderId(out_trade_no);
        order.setPayFrom("123");
        order.setPayTo("345");
        order.setPayWay(1);
        order.setOrderState(0);

        boolean result = orderService.createOrder(order);

        resultJson.put("state", result);

        /**
         try {

         AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do ",
         AlipayConfig.APPID, AlipayConfig.PRIVATE_KEY, "json", AlipayConfig.input_charset, AlipayConfig.PUBLIC_KEY, AlipayConfig.sign_type);

         AlipayTradeAppPayRequest alirequest = new AlipayTradeAppPayRequest();

         String subject = "用户充值";
         String body = "用户预付费充值";

         String out_trade_no = orderService.CreateOrederId(authManager.getUserInfoByToken(request).getUserId());

         AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
         model.setPassbackParams(body); //描述信息  添加附加数据
         model.setSubject(subject); //商品标题
         model.setOutTradeNo(out_trade_no); //商家订单编号
         model.setTimeoutExpress("30m"); //超时关闭该订单时间
         model.setTotalAmount(payQuery.getAmount());  //订单总金额
         model.setProductCode("QUICK_MSECURITY_PAY"); //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
         alirequest.setBizModel(model);
         alirequest.setNotifyUrl(AlipayConfig.NotifyUrl);  //回调地址
         String orderStr = "";

         try {
         //这里和普通的接口调用不同，使用的是sdkExecute
         AlipayTradeAppPayResponse response = alipayClient.sdkExecute(alirequest);
         orderStr = response.getBody();
         //                System.out.println(orderStr);//就是orderString 可以直接给客户端请求，无需再做处理。
         } catch (AlipayApiException e) {
         e.printStackTrace();
         }
         resultJson.put("sing", out_trade_no);
         resultJson.put("orderStr", orderStr);


         } catch (Exception ex) {

         }

         **/
        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }


    /**
     * 支付结果通知
     *
     * @return
     */

    @RequestMapping(value = "/pay/alipay/notify", produces = "application/json;charset=utf-8")
    @ResponseBody
    public void PayResult(HttpServletRequest request) {


        Map requestParams = request.getParameterMap();
        System.out.println("支付宝支付结果通知" + requestParams.toString());
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();

        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com


        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        try {
            //验证签名
            boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.PUBLIC_KEY, AlipayConfig.input_charset, AlipayConfig.sign_type);
            if (flag) {
                if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
//                    //付款金额
//                    String amount = params.get("buyer_pay_amount");
//                    //支付宝交易号
//                    String trade_no = params.get("trade_no");
//                    //附加数据
//                    String passback_params = URLDecoder.decode(params.get("passback_params"));

                    //商户订单号
                    String out_trade_no = params.get("out_trade_no");
                    //updateOrderInfo(out_trade_no,MD5Encode.encode(out_trade_no));
                }
            }
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    @RequestMapping("/createweixinsing")
    public String getWeixinSin() {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();


        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


}
