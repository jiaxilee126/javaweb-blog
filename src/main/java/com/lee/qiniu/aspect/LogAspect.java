package com.lee.qiniu.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lee.qiniu.aspect.annotation.SysLog;
import com.lee.qiniu.entity.OprerationLog;

/**
 * @Description: 日志切面
 * @author Jussi Lee
 * @date 2018年10月25日
 */
@Aspect
@Component
@EnableAsync
public class LogAspect {
	
	@Pointcut("@annotation(com.lee.qiniu.aspect.annotation.SysLog) ")
	public void pointcut(){};
	
	/**
	 * @Description: 通知方法在目标方法返回时调用
	 *  void  
	 * @throws
	 * @author Jussi Lee
	 * @date 2018年10月25日
	 */
	@AfterReturning(pointcut="pointcut()", returning = "rv")
	public void doAfter(JoinPoint joinPoint, Object rv){
		handleLog(joinPoint, rv, null);
	}
	
	/**
	 * @throws ClassNotFoundException 
	 * @Description: 通知方法在目标方法抛出异常时调用
	 *  void  
	 * @throws
	 * @author Jussi Lee
	 * @date 2018年10月25日
	 */
	@AfterThrowing(pointcut = "pointcut()", throwing = "e")
	public void doAfterThrow(JoinPoint joinPoint, Throwable e) throws ClassNotFoundException{
		
		
	}
	
	public SysLog getLog(JoinPoint joinPoint) throws Exception{
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		String targetName = joinPoint.getTarget().getClass().getName();
		Object[] args = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		for(Method method : methods) {
            if(method.getName().equals(methodName)) {
               @SuppressWarnings("rawtypes")
               Class[] clazzs = method.getParameterTypes();
                if(clazzs.length == args.length) {
                	SysLog log = method.getAnnotation(SysLog.class);
                	return log;
               }
           }
		}
		return null;
	}
	
	
	public void handleLog(JoinPoint joinPoint, Object rv, Throwable e){
		try {
			
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
	OprerationLog getOperationInfo(){
		OprerationLog oprerationLog = new OprerationLog();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		request.getRequestURI();
		
	
	
	
		return oprerationLog;
	}
}
