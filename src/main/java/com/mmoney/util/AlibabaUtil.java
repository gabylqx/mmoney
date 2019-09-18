package com.mmoney.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.mmoney.pojo.AlipayConfig;

import java.math.BigDecimal;

/**
 * @program: name 转账姓名
 * @program: account 转帐账户
 * @program: uuid 订单号
 * @program: sum 转账金额
 * @program: memo 备注
 * @description: 转账工具类
 * @author: Li.QiXuan
 * @create: 2019-09-03 15:19
 **/

public class AlibabaUtil {
    public static boolean transfer(String name, String account, String uuid, BigDecimal sum, String memo){
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizContent("{" +
                "\"out_biz_no\":\"" + uuid +"\"," +
                "\"payee_type\":\"ALIPAY_LOGONID\"," +
                "\"payee_account\":\"" +account+ "\"," +//nlndxc9125@sandbox.com
                "\"amount\":\"" + sum + "\"," +
                "\"payer_show_name\":\"上海交通卡退款\"," +
                "\"payee_real_name\":\"" +name+ "\"," +//沙箱环境
                "\"remark\":\"" + memo + "\"" +
                "  }");
        AlipayFundTransToaccountTransferResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
            return true;
        } else {
            System.out.println("调用失败");
            return false;
        }
    }
}
