package com.mmoney.interceptor;

import com.mmoney.dao.UserMapper;
import com.mmoney.pojo.User;
import com.mmoney.util.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginIntercepter extends HandlerInterceptorAdapter {

    private String[] ingore_url = {"getSms.do", "userLoginCheck.do", "codeLoginCheck.do",
            "codeFindCheck.do","userReg.do","js/","layui/","images","css/", "goodsDetail.do", "queryGoods.do"};

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     *    执执行下一个拦截器,直到所有的拦截器都行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Autowired(required = false)
    private UserMapper userMapper ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录状态检测
        System.out.println("拦截器");
        HttpSession session = request.getSession();

        User login= (User) session.getAttribute("loginUser");
        //没有登录处理
        if (login == null) {
            Cookie[] cookies=request.getCookies();
            String tel=null;
            String pswd=null;
            if(cookies!=null){
                System.out.println("cookies length:"+cookies.length);
                for(int i=0;i<cookies.length;i++){

                    String cookieName=cookies[i].getName();
                    if("cptUsrTel".equals(cookieName)){
                        tel=CryptoUtil.decode(cookies[i].getValue());
                    }
                    if("cptUsrPswd".equals(cookieName)){
                        pswd=CryptoUtil.decode(cookies[i].getValue());
                    }
                }
                System.out.println("tel:"+tel+"pswd"+pswd);
                if(tel!=null&&pswd!=null){
                    User user=new User();
                    user.setUsrTel(tel);
                    user.setUsrPswd(pswd);
                    User user1 = userMapper.loginCheck(user);
                    System.out.println(user1);
                    if(user1!=null){
                        request.getSession().setAttribute("loginUser",user1);
                        return true;
                    }else{
                        System.out.println("未通过校检");
                    }
                }else{
                    System.out.println("cookie中未找到账号密码");
                }

            }else{
                System.out.println("Cookie为空");
            }
            String url = request.getRequestURL().toString();
            for (String u : ingore_url) {
                if (url.contains(u)) {
                    System.out.println("未登录规则放行:"+u);
                    return true;
                }
            }
            System.out.println("未登录拦截");
            response.sendRedirect("/");
            return false;
        }
        //管理员后台拦截
            String url = request.getRequestURL().toString();
            if (url.contains("/admin")) {
                System.out.println("后台拦截:"+url);
                if (login.getUsrGrant()==null||!login.getUsrGrant().equals("admin")){
                    response.sendRedirect("/");
                    return false;
                }
            }
        if ("http://mmoney.liqixuan.cn/admin".equals(url)){
            response.sendRedirect("/admin/queryToloan.do");
            return false;
        }
            return true;
    }

    /*
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间


    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle(), 在访问Controller之后，访问视图之前被调用,这里可以注入一个时间到modelAndView中，用于后续视图显示");
        modelAndView.addObject("date","由拦截器生成的时间:" + new Date());
    }

     *
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     *

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        System.out.println("afterCompletion(), 在访问视图之后被调用");
    }

    */
}