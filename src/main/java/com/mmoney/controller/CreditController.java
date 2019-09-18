package com.mmoney.controller;

import com.mmoney.pojo.Credit;
import com.mmoney.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.RequestWrapper;

/**
 * @program: mmoney
 * @description: 信用contro
 * @author: Li.QiXuan
 * @create: 2019-08-26 20:57
 **/
@Controller
public class CreditController {

    @Autowired
    private CreditService creditService;

    @RequestMapping("/selectCreditById.do")
    public String selectCreditById(int cdtId, Model model){
        Credit credit = creditService.selectCreditById(cdtId);
        System.out.println("credit:"+credit);
        model.addAttribute("cdt",credit);
        return "credit/showCredit";
    }

}
