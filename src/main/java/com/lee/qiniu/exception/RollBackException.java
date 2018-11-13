package com.lee.qiniu.exception;

/**
 * @Description: 自定义回滚异常
 * @author Jussi Lee
 * @date 2018年11月12日
 */
public class RollBackException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RollBackException(String msg){
		super(msg);
	}
	
	public RollBackException (Throwable e){
		super(e);
	}
}
