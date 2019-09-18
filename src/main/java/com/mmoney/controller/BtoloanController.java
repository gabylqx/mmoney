package com.mmoney.controller;

import com.mmoney.pojo.Btoloan;
import com.mmoney.service.BtoloanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @program: mmoney
 * @description:
 * @author: Li.QiXuan
 * @create: 2019-08-30 13:43
 **/
@Controller
public class BtoloanController {
    @Autowired
    private BtoloanService btoloanService;

    @RequestMapping("bto/btoPay.do")
    public String btoPay(RedirectAttributes redirectAttributes, Integer btoId, HttpSession session){
        Btoloan btoloan = btoloanService.selectBtoloanById(btoId);
        if (btoloan!=null&&btoloan.getBtoStill()==0){
            session.setAttribute("payBto",btoloan);
            //订单id
            String out_trade_no = UUID.randomUUID().toString().replaceAll("-", "");
            //订单金额
            BigDecimal total_amount = btoloan.getBtoPrin().add(btoloan.getBtoIntes());
            //订单名称
            String subject = "订单号："+btoloan.getBtoTolId()+" 还款";

            redirectAttributes.addAttribute("out_trade_no",out_trade_no);
            redirectAttributes.addAttribute("total_amount",total_amount);
            redirectAttributes.addAttribute("subject",subject);

            session.setAttribute("payId",out_trade_no);
            return "redirect:/aliPay.do";
        }
        return "info/err";
    }

    @RequestMapping("/BtoloanById.do")
    public String btoloanById(String tolId , Model model){
        List<Btoloan> btos = btoloanService.selectBtoloanListById(tolId);
        model.addAttribute("btos",btos);
        BigDecimal bmoney = BigDecimal.ZERO;//总借款
        BigDecimal omoney = BigDecimal.ZERO;//未还借款
        for (Btoloan bto:btos) {
            BigDecimal bd = bto.getBtoPrin().add(bto.getBtoIntes());
            bmoney = bmoney.add(bd);
            if (bto.getBtoStill()==0){
                omoney = omoney.add(bd);
            }
        }
        model.addAttribute("bmoney",bmoney);
        model.addAttribute("omoney",omoney);
        return "personalInfo";
    }

}
