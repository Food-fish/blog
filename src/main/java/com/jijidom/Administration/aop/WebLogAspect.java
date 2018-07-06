package com.jijidom.Administration.aop;

import com.jijidom.util.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

/**
 * @Author: JinTao
 * @Description: 日志拦截器
 * @Date: 12:23 2018/5/22
 */
@Component
@Aspect
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private static ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal StartTime");

    @Pointcut("execution(public * com.jijidom.Administration.web..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //HttpServletResponse response = attributes.getResponse();
        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("请求方式 : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("开始时间 : " + new SimpleDateFormat("hh:mm:ss.SSS").format(beginTime));
        logger.info("目标方法名为:" + joinPoint.getSignature().getName());
        logger.info("目标方法所属类的简单类名:" +        joinPoint.getSignature().getDeclaringType().getSimpleName());
        logger.info("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        logger.info("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        /*Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logger.info("第" + (i+1) + "个参数为:" + args[i]);
        }*/
        logger.info("被代理的对象:" + joinPoint.getTarget());
        logger.info("代理对象自己:" + joinPoint.getThis());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            logger.info("name:{},value:{}", name, request.getParameter(name));
        }
    }

    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 接收到请求，记录请求内容
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis(); 	//2、结束时间
        logger.info("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), DateUtils.formatDateTime(endTime - beginTime),
                request.getRequestURI(), Runtime.getRuntime().maxMemory()/1024/1024, Runtime.getRuntime().totalMemory()/1024/1024, Runtime.getRuntime().freeMemory()/1024/1024,
                (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret.toString());
    }

}
