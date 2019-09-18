package com.mmoney.controller;

import com.alibaba.fastjson.JSON;
import com.mmoney.pojo.Crowdfounding;
import com.mmoney.pojo.Record;
import com.mmoney.pojo.Toloan;
import com.mmoney.pojo.User;
import com.mmoney.service.CrowdfoundService;
import com.mmoney.service.ToloanService;
import com.mmoney.service.UserService;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CrowdfoundController {

    @Autowired
    private CrowdfoundService crowdfoundService;
    @Autowired
    private ToloanService toloanService;
    @Autowired
    private  UserService userService;

    //图片上传服务器
    @RequestMapping("/upImg.do")
    public @ResponseBody  Map upImg(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (!ServletFileUpload.isMultipartContent(req)) {// 检测是否为多媒体上传如果不是则停止
            resp.getWriter().println("Error: 表单必须包含 enctype=multipart/form-data");
            return null;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024 * 3);// 3M设置内存临界值-超过后将产生临时文件并存储于临时目录中
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));// 设置临时存储目录
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(1024 * 1024 * 40);// 设置最大文件上传值 40M
        upload.setSizeMax(1024 * 1024 * 50);// 设置最大请求值 (包含文件和表单数据)50M
        // 构造临时路径来存储上传的文件 这个路径相对当前应用的目录
        ServletContext servletContext = req.getServletContext();
        String filePreix = "images";
        String uploadPath = servletContext.getRealPath("./") + filePreix;
        String fileName = "";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {// 如果目录不存在则创建
            uploadDir.mkdir();
        }
        try {
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {// 处理表单中每一项内容
                    if (!item.isFormField()) {// 处理不在表单中的字段
                        fileName = new File(item.getName()).getName();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
                        fileName = sdf.format(new Date()) + "-" + fileName;

                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        System.out.println(filePath);// 在控制台输出文件的上传路径
                        item.write(storeFile); // 保存文件到硬盘
                        System.out.println("fileName:" + fileName);

                        req.setAttribute("message", "文件上传成功!");
                    }
                }
            }
        } catch (Exception ex) {
            req.setAttribute("message", "错误信息: " + ex.getMessage());
        }
        Map<String,Object> map2=new HashMap<>();
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","ok");
        map.put("data",map2);
        map2.put("src","/images/"+fileName);
        return map;
    }
    //创建众筹项目
    @RequestMapping("/releaseCrowdfound.do")
    public String releaseCrowdfound(Crowdfounding crowdfounding, HttpSession session,String subtxt){
        User user = (User)session.getAttribute("loginUser");
        crowdfounding.setCfStartDate(new Date());
        crowdfounding.setCfUsrId(user.getUsrId());
        crowdfounding.setCfImgpath(subtxt);
        System.out.println(subtxt);
        System.out.println(crowdfounding);
        int rel = crowdfoundService.insertCrowfound(crowdfounding);
        if (rel==1){
            System.out.println("Crowfound insert ok");
        }
        return "redirect:/admin";
    }

    //显示所有众筹项目
    @RequestMapping("/showCrowdfound.do")
    public String showCrowdfound(HttpSession session) {

        List<Crowdfounding> cfList = crowdfoundService.showCrowdfounds();
        List<Crowdfounding> newCfLst = new ArrayList<Crowdfounding>();
        Date nowTime = new Date();//当前时间
        for (int i = 0; i < cfList.size(); i++) {
            Date cfDeline = cfList.get(i).getCfDeline();//截至时间
            Date subDate = new Date(cfDeline.getTime() - nowTime.getTime());
            cfList.get(i).setCfSub(subDate);
            if (cfDeline.compareTo(nowTime) < 0) {
                cfList.remove(i);
                continue;
            }
            newCfLst.add(cfList.get(i));
            if (newCfLst.size()>9)
                break;
        }
        session.setAttribute("cfLists",newCfLst);
        return "crowd";
    }

    //去众筹
    @RequestMapping("/goCrowd.do")
    public String goCrowd(Integer cfId, Integer sub, Model model){
        Crowdfounding cf = crowdfoundService.findCrowdById(cfId);
        model.addAttribute("cfs",cf);
        List<Record> recordList = crowdfoundService.findCrowUsers(cfId);
        model.addAttribute("recordLists",recordList);
        model.addAttribute("sub",sub);
        return "goCrowd";
    }


    //筹款
    @RequestMapping("/raiseAmount.do")
    public String raiseAmount(HttpSession session, BigDecimal fundAmount,Integer cfId,RedirectAttributes redirectAttributes){
        Map map = new HashMap();

        User user = (User)session.getAttribute("loginUser");
        if(user==null){
            return "redirect:/";
        }
            Record record = new Record();
            record.setRcCfId(cfId);
            record.setRcJoinTime(new Date());
            record.setRcMoney(fundAmount);
            record.setRcUsrId(user.getUsrId());

        session.setAttribute("payZc",record);
        //订单id
        String out_trade_no = UUID.randomUUID().toString().replaceAll("-", "");
        //订单金额 fundAmount

        //订单名称
        String subject = user.getUsrName()+"众筹项目付款";

        redirectAttributes.addAttribute("out_trade_no",out_trade_no);
        redirectAttributes.addAttribute("total_amount",fundAmount);
        redirectAttributes.addAttribute("subject",subject);

        session.setAttribute("payId",out_trade_no);
        return "redirect:/aliPay.do";

    }
}
