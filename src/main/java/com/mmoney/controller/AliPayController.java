package com.mmoney.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.mmoney.pojo.*;
import com.mmoney.service.BtoloanService;
import com.mmoney.service.CrowdfoundService;
import com.mmoney.service.ToloanService;
import com.mmoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: mmoney
 * @description:
 * @author: Li.QiXuan
 * @create: 2019-08-30 10:33
 **/

@Controller
public class AliPayController {

    @Autowired
    private BtoloanService btoloanService;
    @Autowired
    private ToloanService toloanService;
    @Autowired
    private UserService userService;
    @Autowired
    private CrowdfoundService crowdfoundService;

    //生成有二维码，可供扫码支付的页面
    @RequestMapping(value = "aliPay.do")
    public String aliPay(HttpServletResponse response, String chapterId,
                         String out_trade_no, String total_amount, String subject, String WIDbody) throws  IOException, AlipayApiException {
//   String a,String urlName,String couName....+"&a="+a+"&urlName="+urlName+"&couName="+couName
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url+"?chapterId="+chapterId);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //付款ID，必填out_trade_no
        //付款金额，必填total_amount
        //订单名称，必填subject
        total_amount=URLDecoder.decode(total_amount,"UTF-8");//转码
        subject= URLDecoder.decode(subject,"UTF-8");
        //商品描述，可空
        String body = WIDbody;

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\"1m\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(result);
        return null;
    }

    //验证后支付状态
    @RequestMapping("paySource.do")
    public String paySource( HttpServletRequest req,HttpSession session) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        Map requestParams = req.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
        // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
        // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }

        // 获取支付宝的通知返回参数
        // 商户订单号
        String out_trade_no = new String(req.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        System.out.println("订单id"+out_trade_no);
        System.out.println("信息"+params);
/*        可以通过这块代码得到通知的数据，转换之后的map数据在params 里边，也可以直接从request里读取数据如上的订单号。
        之后根据这些参数做验签，存储，更新等操作*/
        String payId = (String) session.getAttribute("payId");
        session.removeAttribute("payId");
        Btoloan btoloan = (Btoloan) session.getAttribute("payBto");
        Record record = (Record) session.getAttribute("payZc");

        System.out.println("payid"+payId);
        System.out.println(btoloan);
        if (btoloan!=null&&payId.equals(out_trade_no)){//验证订单号
            session.removeAttribute("payBto");
            User user = (User) session.getAttribute("loginUser");
            user = userService.queryUserByUser(user);
            Double amount = Double.valueOf(params.get("total_amount"));
            BigDecimal bigamount = BigDecimal.valueOf(amount);
            BigDecimal btoamount = btoloan.getBtoIntes().add(btoloan.getBtoPrin());
            System.out.println("big:"+bigamount+"   bto:"+btoamount);

            if (bigamount.compareTo(btoamount)==0){//验证交易金额
                btoloan.setBtoStill(1);
                if (btoloanService.updateBtoloan(btoloan)){//验证还款项是否插入成功
                    Toloan toloan = toloanService.selectToloansById(btoloan.getBtoTolId());//获取主表对象

                    toloan.setTolOmoney(toloan.getTolOmoney().subtract(btoloan.getBtoPrin()));//更新主订单未还金额
                    if (toloan.getTolOmoney().compareTo(BigDecimal.ZERO) < 1){
                        toloan.setTolStill(1);
                    }
                    toloanService.updatetoloan(toloan);
                    //跟新数据库
                    user.setUsrExquota(user.getUsrExquota().subtract(btoloan.getBtoPrin()));//更新用户可用额度
                    userService.updateUserByUser(user);//跟新数据库
                    session.setAttribute("loginUser",user);
                    System.out.println(user);
                    System.out.println(toloan);
                    return "redirect:/BtoloanById.do?tolId="+toloan.getTolId();
                }else
                    req.getServletPath();
                    req.getServerName();
                    req.getPathInfo();

                    return "info/payErr";

            }
        }

        if (record!=null&&payId.equals(out_trade_no)){//验证订单号
            session.removeAttribute("payZc");
            User user = (User) session.getAttribute("loginUser");
            user = userService.queryUserByUser(user);
            BigDecimal bigamount = new BigDecimal(params.get("total_amount"));//付款金额
            BigDecimal recamount = record.getRcMoney();//订单金额

            if (bigamount.compareTo(recamount)==0){//验证交易金额
                if (crowdfoundService.insertRecord(record)>0){//验证是否插入成功
                    Crowdfounding crowd = crowdfoundService.findCrowdById(record.getRcCfId());//主表
                    BigDecimal cfMoney = crowd.getCfMoney();
                    if (cfMoney==null)
                        cfMoney = BigDecimal.ZERO;
                    cfMoney = cfMoney.add(record.getRcMoney());
                    crowd.setCfMoney(cfMoney);
                    try {
                        crowdfoundService.updateCfMoney(crowd);
                    }catch (Exception e){
                        return "info/payErr";
                    }
                    int days = (int) ((crowd.getCfDeline().getTime() - new Date().getTime()) / (1000*3600*24));
                    return "redirect:/goCrowd.do?cfId="+record.getRcCfId()+"&sub="+(days+1);
                }else
                return "info/payErr";

            }
        }
        return "info/err";
    }
}
