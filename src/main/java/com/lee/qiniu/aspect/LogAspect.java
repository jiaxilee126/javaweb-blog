package com.lee.qiniu.aspect;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.qiniu.aspect.annotation.SysLog;
import com.lee.qiniu.dao.OperationLogDao;
import com.lee.qiniu.entity.OperationLog;

/**
 * @Description: 日志切面
 * @author Jussi Lee
 * @date 2018年10月25日
 */
@Aspect
@Component
@EnableAsync
public class LogAspect {
	@Autowired
	private OperationLogDao operationLogDao;
	
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
		handleLog(getOperationInfo(),joinPoint, rv, null);
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
		handleLog(getOperationInfo(),joinPoint, null, e);
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
	
	
	public void handleLog(OperationLog log,JoinPoint joinPoint, Object rv, Throwable e){
		try {
			if(e != null){
				log.setSussess(false);
				log.setErrorMsg(e.getMessage());
			}else {
				log.setSussess(true);
			}
			SysLog sysLog = getLog(joinPoint);
			log.setActionName(sysLog.module());
			log.setMethodName(sysLog.type().getName());
			operationLogDao.insert(log);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	private OperationLog getOperationInfo(){
		OperationLog oprerationLog = new OperationLog();
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			oprerationLog.setLogDateTime(new Date());
			oprerationLog.setOperUrl(request.getRequestURI());
			oprerationLog.setOperIp(request.getRemoteAddr());
			ObjectMapper objectMapper = new ObjectMapper();
			
			Map<String, String[]> params = request.getParameterMap();
			oprerationLog.setParams(objectMapper.writeValueAsString(params));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return oprerationLog;
	}
}
