package com.pc.resolver;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class MyExceptionResolver implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        System.out.println("异常处理器");
        System.out.println(e.getClass());
        //e.printStackTrace();
        ModelAndView mv = new ModelAndView();
        if (e instanceof IncorrectCredentialsException) {
            mv.addObject("msg", "密码错误");
            mv.setViewName("redirect:/login");
        }
        else if (e instanceof UnknownAccountException) {
            mv.addObject("msg", "用户名错误");
            mv.setViewName("redirect:/login");
        }
        return mv;
    }
}
