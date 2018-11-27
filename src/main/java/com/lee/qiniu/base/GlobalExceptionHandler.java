package com.lee.qiniu.base;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lee.qiniu.exception.RollBackException;
import com.lee.qiniu.json.JsonResult;
import com.lee.qiniu.json.ResultCodeEnum;

/**
 * @Description: 全局异常处理类
 * @author Jussi Lee
 * @date 2018年10月17日
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
	
	@ResponseBody
	@ExceptionHandler({RuntimeException.class})
	public JsonResult handlerException(RuntimeException e){
		logger.error("运行时异常"+e.getMessage());
		return JsonResult.code(ResultCodeEnum.SYSTEM_UNKNOWN_ERROR);
	}
	
	@ResponseBody
	@ExceptionHandler({TypeMismatchException.class})
	public  JsonResult handlerException(TypeMismatchException e){
		logger.error("参数类型不匹配异常"+e.getMessage());
		return JsonResult.code(ResultCodeEnum.DATAPLAT_REQ_ERROT);
	}
	
	@ResponseBody
	@ExceptionHandler({RollBackException.class})
	public JsonResult handlerException(RollBackException e) {
		logger.error("数据库执行异常"+e.getMessage());
		return JsonResult.code(ResultCodeEnum.DATABASE_EXCEPTION);
	}
	
	
	/**
	 * @Description: 对于未授权的页面进行限制
	 * @return String  
	 * @throws
	 * @author Jussi Lee
	 * @date 2018年11月27日
	 */
	@ExceptionHandler({UnauthorizedException.class})
	public String unAuthorized() {
		return "/other/deny";
	}
}
