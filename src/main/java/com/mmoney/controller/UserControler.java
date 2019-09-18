package com.mmoney.controller;

import com.alibaba.fastjson.JSON;
import com.mmoney.global.SendSms;
import com.mmoney.pojo.Credit;
import com.mmoney.pojo.User;
import com.mmoney.service.CreditService;
import com.mmoney.service.ToloanService;
import com.mmoney.service.UserService;
import com.mmoney.util.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserControler {

    @Autowired
    private UserService userService;

    @Autowired
    private CreditService creditService;

    @RequestMapping("userLoginCheck.do")
    public @ResponseBody Map userLoginCheck( HttpServletResponse resp, HttpServletRequest req, HttpSession session){
        String hello = req.getParameter("hello");
        System.out.println(hello);
        Map parse = (Map) JSON.parse(hello);
        System.out.println(parse);
        String usrTel = (String) parse.get("usrTel");
        String usrPswd = (String) parse.get("usrPswd");
        String autoLogin = (String) parse.get("autoLogin");
        User user = new User();
        //System.out.println("usrTel:"+usrTel+"  usrPswd:"+usrPswd+"    autoLogin:"+autoLogin);//测试
        user.setUsrTel(usrTel);
        user.setUsrPswd(usrPswd);
        User userTure = userService.loginCheck(user);
        Map map = new HashMap();
        System.out.println(userTure);
        if (userTure!=null) {
            String cptUsrTel = CryptoUtil.encode(usrTel);//电话加密
            String cptUsrPswd = CryptoUtil.encode(usrPswd);//密码加密

            //存入cookie
            if ("on".equals(autoLogin)){
                Cookie cookieSta = new Cookie("autoLogin",autoLogin);
                cookieSta.setMaxAge(3600*24*7);
                resp.addCookie(cookieSta);
                Cookie cookieTel = new Cookie("cptUsrTel",cptUsrTel);
                cookieTel.setMaxAge(3600*24*7);
                resp.addCookie(cookieTel);
                Cookie cookiePswl = new Cookie("cptUsrPswd",cptUsrPswd);
                cookiePswl.setMaxAge(3600*24*7);
                resp.addCookie(cookiePswl);
            }else{
                Cookie cookieSta = new Cookie("autoLogin","off");
                cookieSta.setMaxAge(0);
                resp.addCookie(cookieSta);
                Cookie cookieTel = new Cookie("cptUsrTel","");
                cookieTel.setMaxAge(0);
                resp.addCookie(cookieTel);
                Cookie cookiePswl = new Cookie("cptUsrPswd","");
                cookiePswl.setMaxAge(0);
                resp.addCookie(cookiePswl);
            }

            session.setAttribute("loginUser",userTure);//用户存入session域中
            map.put("msg","登录成功");
            return map;
        }
        else {
            map.put("msg","登录失败！请重试");
            return map;
        }
    }

    //获取验证码（ajax 返回josn）
    @RequestMapping("/getSms.do")
    public @ResponseBody Map getSms(String usrTel,String sat, HttpSession session){
        System.out.println("usrTel:"+usrTel+"  sat"+ sat);//测试数据是否拿到
        Integer verCode = (int)((Math.random()*9+1)*1000);//产生4位验证码
        System.out.println("verCode:"+verCode);
       // String staCode ="100";//得到状态码测试，不能删。正式上线改
        String staCode;
        System.out.println(sat);
        Map map  = new HashMap();
        User user = userService.queryUserByTel(usrTel);
        if (user==null&&(sat.equals("isLogn"))){
            map.put("stacodes",208);
            return map;
        }if (user!=null&&(sat.equals("isReg"))){
            map.put("stacodes",209);
            return map;
        }
        staCode = SendSms.sendCode(verCode,usrTel);
        System.out.println(user);
        System.out.println(staCode);
        //staCode = SendSms.sendCode(verCode, usrTel);//得到状态码
        session.setAttribute("verCodeBf", verCode);//验证码存入session中
        session.setAttribute("usrTelBf", usrTel);//手机号存入session中
        System.out.println("验证码测试："+verCode);

        map.put("stacodes",Integer.parseInt(staCode.trim()));
        return map;
    }

    //用户注册
    @RequestMapping("userReg.do")
    public @ResponseBody Map userReg(HttpServletRequest req,HttpSession session){
        String hello = req.getParameter("hello");
        Map parse = (Map)JSON.parse(hello);
        String usrTel = (String) parse.get("usrTel");
        String usrPswd = (String) parse.get("usrPswd");
        String verCodeStr = (String) parse.get("verCode");
        Integer verCode = Integer.parseInt(verCodeStr);
        Integer verCodeBf =(Integer)session.getAttribute("verCodeBf");// 从session中取验证码
        String usrTelBf = (String) session.getAttribute("usrTelBf");
        session.removeAttribute("usrTelBf");
        session.removeAttribute("verCodeBf");
        Map map = new HashMap();
        if (verCodeBf==null||usrTelBf==null||usrTel==null||usrPswd==null||verCodeStr==null||usrTel.length()<11) {
            map.put("regMsg", "有未输入的信息或未获取验证码，请检查");
            return map;
        }
        if(verCode.equals(verCodeBf) && usrTelBf.equals(usrTel)){
            User user = new User();
            user.setUsrTel(usrTel);
            user.setUsrPswd(usrPswd);
            int rel = userService.userReg(user);
            if(rel == 1){
                map.put("regMsg","注册成功");
                return map;
            }else{
                map.put("regMsg","注册失败");
                return map;
            }
        }else{
            map.put("regMsg","验证码输入错误,请重新书输入");
            return map;
        }
    }

    //用户安全退出
    @RequestMapping("exitLogin.do")
    public String exitLogin(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/";
    }

    //授信
    @RequestMapping("usrGetInit.do")
    public String userReg(Model model, String usrIdCard, String usrName, String usrJob, String usrSalary, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        user.setUsrIdcard(usrIdCard);//身份证
        user.setUsrName(usrName);//名称
        user.setUsrJob(usrJob);//职业
        user.setUsrSalary(usrSalary);//收入
        System.out.println(user);
        if (user.getUsrQuota()==null){
            Map<String,String> info = new HashMap<>();
            info.put("idCode",user.getUsrIdcard());//身份证
            info.put("occ",user.getUsrJob());//职业
            info.put("income",user.getUsrSalary());//收入
            System.out.println(info);
            int creditCode = creditService.usrCreditinit(info);
            System.out.println("crd:"+creditCode);
            if (creditCode>0){
                Credit credit = new Credit(user.getUsrId(),user.getUsrCredit(),creditCode);
                System.out.println("cred:"+credit);
                user.setUsrCredit(creditCode);

                //通过信用分授于额度
                user.setUsrRate(userService.rateUpdate(creditCode));

                creditService.addcredit(credit);//更新成功后插入记录

                //去授予额度
                BigDecimal credits = userService.creditUpdate(creditCode);
                user.setUsrQuota(credits);
                model.addAttribute("credits",credits);
                userService.updateUserByUser(user);//更新user对到数据库
                session.setAttribute("loginUser",user);//对象存入session
                System.out.println(credit);

                //返回成功页面
                return "info/initOk";
            }

        }

        return "info/err";
    }

    //密码找回验证码验证
    @RequestMapping("codeFindCheck.do")
    public @ResponseBody Map codeFindCheck(HttpSession session,HttpServletRequest req) {
        Map map = new HashMap();
        String hello = req.getParameter("hello");
        System.out.println(hello);
        Map parse = (Map)JSON.parse(hello);
        System.out.println(parse);
        String usrTel = (String) parse.get("usrTel");
        String usrPswd = (String) parse.get("usrPswd");
        String verCodeStr = (String)parse.get("verCode");
        int verCode = Integer.parseInt(verCodeStr);
        String usrTelBf =(String)session.getAttribute("usrTelBf");
        int verCodeBf =(Integer)session.getAttribute("verCodeBf");
        session.removeAttribute("usrTelBf");
        session.removeAttribute("verCodeBf");
        if (usrTel.equals(usrTelBf)&&(verCode==verCodeBf)) {
            User user = new User();
            user.setUsrTel(usrTel);
            user.setUsrPswd(usrPswd);
            int rel = userService.updateUserPswdByUser(user);
            if (rel==1){
                map.put("msg","修改成功!");
                return map;
            }else{
                map.put("msg","，服务器错误，修改失败!");
                return map;
            }
        }else
            map.put("msg","手机号或验证码填写错误!");
        return map;
    }

    //验证码登录校验
    @RequestMapping("codeLoginCheck.do")
    public @ResponseBody Map codeLoginCheck(HttpSession session,HttpServletRequest req){
        Map map = new HashMap();
        String hello = req.getParameter("hello");
        System.out.println(hello);
        Map parse = (Map)JSON.parse(hello);
        String usrTel = (String)parse.get("usrTel");
        String verCodeStr = (String)parse.get("verCode");
        int verCode = Integer.parseInt(verCodeStr);
        String usrTelBf =(String)session.getAttribute("usrTelBf");
        int verCodeBf =(Integer)session.getAttribute("verCodeBf");
        session.removeAttribute("usrTelBf");
        session.removeAttribute("verCodeBf");
        //System.out.println("usrTel:"+usrTel+" verCode:"+verCode+"  usrTelBf:" +usrTelBf+"   verCodeBf:"+verCodeBf);//测试
        if (usrTel.equals(usrTelBf)&&(verCode==verCodeBf)){
            User user = userService.queryUserByTel(usrTel);
            if (user!=null) {
                session.setAttribute("loginUser",user);//用户存入session域中
                map.put("msg","登录成功");
                return map;
            }else{
                map.put("msg","用户不存在，请注册");
                return map;
            }
        }else{
            map.put("msg","验证码输入错误");
            return map;
        }

    }


}
