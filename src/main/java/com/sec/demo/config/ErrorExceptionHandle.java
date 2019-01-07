package com.sec.demo.config;



import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;



@ControllerAdvice
public class ErrorExceptionHandle {
 
    private static final Logger log = LoggerFactory.getLogger(ErrorExceptionHandle.class);
 
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(Exception e){
        log.info("----自定义异常处理 ---- exception---");
        ModelAndView m =new ModelAndView();
        m.addObject("Exception",e.getMessage());
        m.setViewName("error/error-400");
        return m;
    }
 
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(RuntimeException e){
        log.info("----自定义RuntimeException异常处理 ---- exception---");
        ModelAndView m =new ModelAndView();
        m.addObject("Exception",e.getMessage());
        m.setViewName("error/error-500");
        return m;
    }


    @ExceptionHandler(value = AuthorizationException.class)
    public String handleAuthorizationException() {
        return "error/error-403";
    }
}
