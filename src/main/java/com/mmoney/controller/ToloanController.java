package com.mmoney.controller;


import com.mmoney.global.SendSms;
import com.mmoney.pojo.Btoloan;
import com.mmoney.pojo.PageChoice;
import com.mmoney.pojo.Toloan;
import com.mmoney.pojo.User;
import com.mmoney.service.BtoloanService;
import com.mmoney.service.ToloanService;
import com.mmoney.service.UserService;
import com.mmoney.util.AlibabaUtil;
import com.mmoney.util.InterestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

import static com.mmoney.util.AlibabaUtil.transfer;
import static java.math.BigDecimal.ROUND_HALF_DOWN;

@Controller
public class ToloanController {

    @Autowired
    private UserService userService;
    @Autowired
    private ToloanService toloanService;
    @Autowired
    private BtoloanService btoloanService;
    private Integer pageSize = 10;


    //借贷信息
    //个人中心
    @RequestMapping("personal.do")
    public String personal(HttpSession session, Model model, Integer pageNow) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null)
            return "redirect:/";
        List<Toloan> toloans = toloanService.selectToloans(user.getUsrId());
        BigDecimal bmoney = BigDecimal.ZERO;//总借款
        BigDecimal omoney = BigDecimal.ZERO;//未还借款
        int sum = 0;//未还笔数
        if (toloans != null) {
            for (Toloan toloan : toloans) {
                omoney = omoney.add(toloan.getTolOmoney());
                bmoney = bmoney.add(toloan.getTolBmoney());
                if (toloan.getTolStill() == 0)
                    sum++;
            }
        }
        if (pageNow == null)
            pageNow = 1;
        System.out.println("pageNow:" + pageNow);
        PageChoice pc = new PageChoice();
        pc.setPageNow(pageNow);
        pc.setPageSize(pageSize);
        pc.setId(user.getUsrId());
        List<Toloan> toloansList = toloanService.queryToloan(pc);
        for (Toloan toloan : toloansList) {
            System.out.println(toloan);
        }
        model.addAttribute("pc", pc);
        model.addAttribute("tlsList", toloansList);
        model.addAttribute("userInfo", user);
        model.addAttribute("bmoney", bmoney);
        model.addAttribute("omoney", omoney);
        model.addAttribute("count", toloans.size());
        model.addAttribute("sum", sum);
        return "personal";
    }

    //生成借贷订单
    @RequestMapping("showOrders.do")
    public String showOrders(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        User user = userService.queryUserByUser(loginUser);
        if (user.getUsrQuota() == null)
            return "borInit";
        System.out.println("user:" + user);
        session.setAttribute("User", user);
        return "borrowMoney";
    }


    //添加借贷订单
    @RequestMapping("addOrders.do")
    public String addOrders(String tolBmoney, Integer tolBstages, HttpSession session) {
        System.out.println("tolBmoney:" + tolBmoney + " tolBstages:" + tolBstages);
        User user = (User) session.getAttribute("User");
        BigDecimal bmoney = new BigDecimal(tolBmoney);//借款金额
        //查询user对象
        user = userService.queryUserByUser(user);
        System.out.println("usrExquota:" + user.getUsrExquota());
        //借贷余额
        BigDecimal balance = user.getUsrQuota().subtract(user.getUsrExquota());
        System.out.println("balance:" + balance);
        //与0比较
        int i = balance.compareTo(new BigDecimal(tolBmoney));
        if (i > 0) {//大于零

            Toloan toloan = new Toloan();
            toloan.setTolBmoney(bmoney);//存入借款金额
            toloan.setTolOmoney(bmoney);//存入单笔未还金额
            toloan.setTolBstages(tolBstages);//存入分期
            toloan.setTolUsrId(user.getUsrId());//存入用户id
            System.out.println("toloan:" + toloan);
            String tolId = UUID.randomUUID().toString().replaceAll("-", "");
            toloan.setTolId(tolId);

            int toloanOk = toloanService.insertToloan(toloan);
            if (toloanOk == 1) {
                Map map = InterestUtil.monthlyEquivalence(bmoney, user.getUsrRate(), tolBstages);
                Map inte = (Map) map.get("inte");
                Date dt = new Date();
                Calendar c = Calendar.getInstance();
                Btoloan btoloan = new Btoloan();
                btoloan.setBtoTolId(tolId);
                btoloan.setBtoPrin(bmoney.divide(new BigDecimal(inte.size()), 2, ROUND_HALF_DOWN));//每期本金
                //转账到用户
                boolean tra = transfer("沙箱环境","nlndxc9125@sandbox.com",tolId,bmoney,"用户"+user.getUsrName()+"借款,分"+inte.size()+"期");
                //短信通知
                SendSms.sendTxt(bmoney+"元,分"+inte.size()+"期成功，请查收",user.getUsrTel());
                if (tra){
                for (i = 1; i <= inte.size(); i++) {
                    BigDecimal bolInte = new BigDecimal((String) inte.get(i));
                    //set利息  期数
                    btoloan.setBtoIntes(bolInte);
                    btoloan.setBtoPeriod(i + "/" + tolBstages);
                    c.setTime(dt);
                    c.add(Calendar.MONTH, i);
                    btoloan.setBtoFdate(c.getTime());
                    if (i == inte.size()) {
                        BigDecimal bd = btoloan.getBtoPrin().multiply(new BigDecimal(inte.size()));
                        bd = bmoney.subtract(bd);
                        btoloan.setBtoPrin(btoloan.getBtoPrin().add(bd));
                        System.out.println(bd);
                    }
                    btoloanService.insertBtoloan(btoloan);
                }

                    System.out.println("toloan ok");
                    user.setUsrExquota(user.getUsrExquota().add(bmoney));//已用额度
                    int userOk = userService.updateUserByUser(user);
                    session.setAttribute("loginUser", user);
                    if (userOk == 1) {
                        System.out.println("User ok");
                    }
                }
            }
        }
        return "index";
    }


}
