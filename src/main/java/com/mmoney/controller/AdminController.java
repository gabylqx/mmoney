package com.mmoney.controller;

import com.mmoney.pojo.Btoloan;
import com.mmoney.pojo.PageChoice;
import com.mmoney.pojo.Toloan;
import com.mmoney.service.AdminService;
import com.mmoney.util.LayuiTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    //管理员信息总览
    @RequestMapping("admin/queryToloan.do")
    public String queryToloan(Model model){
        List<Toloan> toloanList = adminService.queryToloanDetil();
        //借款总数 toloanNum
        //未还款总数 notRtLoanNum
        //借款总金额 money
        //未还款总金额 Omoney
        //利息 intes
        Integer toloanNum = toloanList.size();
        int notRtLoanNum = 0;
        BigDecimal money = new BigDecimal("0");
        BigDecimal omoney = new BigDecimal("0");
        BigDecimal intes = adminService.queryIntes();
        BigDecimal inte = adminService.queryInte();
        for(Toloan t : toloanList){
            money =  money.add(t.getTolBmoney());
            if(t.getTolStill()== 0){
                omoney = omoney.add(t.getTolOmoney());
                notRtLoanNum++;
            }
        }


/*        session.setAttribute("notRtLoanNum",notRtLoanNum);
        session.setAttribute("toloanNum",toloanNum);
        session.setAttribute("omoney",omoney);
        session.setAttribute("money",money);
        session.setAttribute("intes",intes);*/

        model.addAttribute("toloanNum",toloanNum);//借款总笔数
        model.addAttribute("money",money); //借款总金额
        model.addAttribute("omoney",omoney);//未还款总金额
        model.addAttribute("notRtLoanNum",notRtLoanNum);//未还款总数
        model.addAttribute("intes",intes);
        model.addAttribute("inte",inte);


        return "admin/index";
    }
    //查询细节
    @ResponseBody
    @RequestMapping(value = "admin/queryToloanDetil.do",produces = "application/json")
    public LayuiTable queryToloanDetil(Integer page,Integer limit){

        if (page==null||page<=0){
            page=1;
        }
        if (limit == null){
            limit = 10;
        }

        PageChoice pageChoice = new PageChoice();
        pageChoice.setPageNow(page);
        pageChoice.setPageSize(limit);
        pageChoice.setBeginRow((pageChoice.getPageNow() - 1)* pageChoice.getPageSize());
        List<Toloan> toloanList = adminService.queryToloanDetil();
        List<Toloan> toloanListPart = adminService.queryToloanDetilPart(pageChoice);
        return LayuiTable.tableData(toloanList.size(),toloanListPart);

    }
    //每个订单详细信息
    @ResponseBody
    @RequestMapping(value = "admin/queryBtolanById.do",produces = "application/json")
    public LayuiTable queryBtolanById(String tolId , Model model, HttpServletResponse response){
        List<Btoloan> btoloanList = adminService.queryBtolanById(tolId);
        if(btoloanList == null){
            model.addAttribute("BtoloanList","没有记录");
        }else{
            model.addAttribute("BtoloanList",btoloanList);
        }
        return LayuiTable.tableData(btoloanList.size(),btoloanList);
    }
}
