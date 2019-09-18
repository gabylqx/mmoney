package com.mmoney.controller;


import com.mmoney.pojo.Overdue;
import com.mmoney.pojo.User;
import com.mmoney.service.OverdueService;
import com.mmoney.service.UserService;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: mmoney
 * @description: 信用contro
 * @author: Li.QiXuan
 * @create: 2019-08-26 20:57
 **/
@Controller
public class OverdueController {

    @Autowired
    private OverdueService overdueService;

    @RequestMapping("/admin/odeTotalQuery.do")
    public String odeTotalQuery(String usrTel,Model model)
    {
        System.out.println(usrTel);
        List<Overdue> odeTotals=overdueService.odeTotalQuery(usrTel);
        model.addAttribute("odeTotals",odeTotals);

        return "/admin/showOverdue";
    }

    @ResponseBody
    @RequestMapping(value = "/overdueQuery.do",produces = "application/json")
    public List<Overdue> overdueQuery(@RequestParam("usrTel") String usrTel)
    {
        System.out.println(usrTel);
        List<Overdue> overdues=overdueService.overdueQueryByTel(usrTel);


        return overdues;
    }
}
