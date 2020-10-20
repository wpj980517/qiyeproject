package itcast.controller;

import itcast.domain.sysLog;
import itcast.service.ILogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

//配置方法aop
//@Component
//@Aspect
public class LogAop {
    private Date visitTime;
    private Class clazz;
    private Method method;
    //获取request对象然后获取url
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ILogService service;

    //配置前置通知
    @Before("execution(* itcast.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {

        visitTime=new Date();//获取当前访问的时间
        clazz=jp.getTarget().getClass();//获取被代理类的类文件
        String methodName=jp.getSignature().getName();//获取方法名称
        Object[] args=jp.getArgs();//获取参数
        if(args==null || args.length==0){
            method=clazz.getMethod(methodName,null);//获取无参的方法
        }else {
                Class[] classArgs=new Class[args.length];
            for(int i=0;i<classArgs.length;i++)
                classArgs[i]=args[i].getClass();//获取参数的类
                method=clazz.getMethod(methodName, classArgs);//获取有参的方法
        }
    }

    @After("execution(* itcast.controller.*.*(..))")
    public void doAfter(JoinPoint jp){
        long time=new Date().getTime()- visitTime.getTime();//获取访问时长

        String url="";
        if(clazz!=null&&method!=null&clazz!=LogAop.class){
            //获取类上的注解
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(clazzAnnotation!=null){
                //获取注解value
                String[] clazzValue=clazzAnnotation.value();
                //获取方法注解
                RequestMapping methodAnnotation=method.getAnnotation(RequestMapping.class);
                //获取方法注解value
                String[] methodValue=methodAnnotation.value();

                //拼接成url
                url=clazzValue[0]+methodValue[0];
            }

            System.out.println(url);
            //获取ip
            String ip=request.getRemoteAddr();
            //获取当前操作用户
            SecurityContext context= SecurityContextHolder.getContext();//获取context
            User user = (User) context.getAuthentication();//获取操作对象
            String username=user.getUsername();//获取username

            //将所有东西封装到LogAop中
            sysLog log=new sysLog();
            log.setExecutionTime(time);
            log.setUsername(username);
            log.setMethod(method.getName());
            log.setUrl(url);
            log.setVisitTime(visitTime);
            log.setIp(ip);

            service.saveLog(log);
        }

    }

}
