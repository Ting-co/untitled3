package com.javawebtest.web;

import com.javawebtest.bean.User;
import com.javawebtest.service.UserService;
import com.javawebtest.service.impl.UserServiceImpl;
import com.javawebtest.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    protected void loginout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁seesion
        req.getSession().invalidate();
        //重定向到首页
        resp.sendRedirect(req.getContextPath());
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求的参数
        /*String username = req.getParameter("username");
        String password = req.getParameter("password");
        */
        /*第四版 ,所以就不用一个个的调用req.getParameter("username");*/
        User user= WebUtils.copyBeanUtils(req.getParameterMap(),new User());

        // 调用 userService.login()登录处理业务
        User loginUser = userService.login(user);
        System.out.println(user);
        System.out.println(loginUser);
        // 如果等于null,说明登录 失败!
        if (loginUser == null) {
            // 把错误信息，和回显的表单项信息，保存到Request域中
            req.setAttribute("msg","用户或密码错误！");
            req.setAttribute("username", user);
            //   跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 登录 成功
            //保存登录后的用户名
            req.getSession().setAttribute("User",user);
            //跳到成功页面login_success.html
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }


    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求的参数
        /*String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        */

        /*获取验证码*/
        String attribute = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");



        /*第一版
        User user = new User();
        WebUtils.copyBeanUtils(req,user);*/

        /*第二版
        User user = new User();
        WebUtils.copyBeanUtils(req.getParameterMap(),user);*/

       /*第三版
       User user=(User) WebUtils.copyBeanUtils(req.getParameterMap(),new User());*/
       /*第四版*/
       User user= WebUtils.copyBeanUtils(req.getParameterMap(),new User());



//        2、检查 验证码是否正确  === 写死,要求验证码为:abcde
        if (attribute.equalsIgnoreCase(code)) {
//        3、检查 用户名是否可用
            if (userService.existsUsername(user.getUsername())) {
                System.out.println("用户名[" + user.getUsername() + "]已存在!");

                // 把回显信息，保存到Request域中
                req.setAttribute("msg", "用户名已存在！！");
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email",user.getEmail());

//        跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //      可用
//                调用Sservice保存到数据库
                userService.registUser(user);
//
//        跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            // 把回显信息，保存到Request域中
            req.setAttribute("msg", "验证码错误！！");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());

            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }
}
