package com.mmoney.global;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @program: mmoney
 * @description: 发送短信
 *返回发送的状态码说明：
    状态码		说明
    100			发送成功
    101			验证失败
    102			手机号码格式不正确
    103			会员级别不够
    104			内容未审核
    105			内容过多
    106			账户余额不足
    107			Ip受限
    108			手机号码发送太频繁，请换号或隔天再发
    109			帐号被锁定
    110			手机号发送频率持续过高，黑名单屏蔽数日
    120			系统升级
 * @author: Li.QiXuan
 * @create: 2019-08-27 13:25
 **/

public class SendSms {


    public static String SMS(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }

/**
 *发送验证码
 * 传入参数：
 *      code-要发送的验证码
 *      tel-要发送的手机号
 * 返回值：
 *      ret-状态码（详细对应关系请看最上面）
*/
    public static String sendCode(int code,String tel) {
        String PostData = null;
        try {
            PostData = "account=用户名&password=密码&mobile="+tel
                    +"&content="+java.net.URLEncoder.encode("您的验证码是：【"+code
                    +"】。请不要把验证码泄露给其他人。如非本人操作，可不用理会！","utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String s = SendSms.SMS(PostData, "http://sms.106jiekou.com/utf8/sms.aspx");
        if (s!=null)
            return s;
        else {
            System.out.println("异常 重发短信");
            return sendCode(code,tel);
        }
    }

/**
 *发送借款详情
 * 传入参数：
 *      txt-要发送的
 *      tel-要发送的手机号
 * 返回值：
 *      状态码（详细对应关系请看最上面）
*/
    public static String sendTxt(String txt,String tel){
        String PostData = null;
        try {
            PostData = "account=用户名&password=密码&mobile="+tel
                    +"&content="+java.net.URLEncoder.encode("您的订单："+txt+"。请登录网站查看。","utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  SendSms.SMS(PostData, "http://sms.106jiekou.com/utf8/sms.aspx");
    }

}
