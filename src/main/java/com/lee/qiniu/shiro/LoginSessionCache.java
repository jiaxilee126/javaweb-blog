package com.lee.qiniu.shiro;

import java.util.Date;

import com.lee.qiniu.utils.CommonUtil;

public class LoginSessionCache {

	public static final String KEY_SESSION_LOGIN = "key_session_login";
	
	/**
	 * 绑定一个用户id
	 */
	private Integer id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 登录时间
	 */
	private Date loginDate;
	/**
	 * 是否登录
	 */
	private boolean loginState;
	
	
	/**
	 * 登录验证码超时时间：5分钟
	 */
	private static final int LOGIN_CAPTCHA_TIMEOUT = 5 * 60 * 1000;
	/**
	 * 是否需要验证验证码
	 */
	private boolean checkCaptcha;
	/**
	 * 登录验证码 生成时间
	 */
	private Date loginCaptchaTime;	
	/**
	 * 登录验证码字符串
	 */
	private String loginCaptchaString;
	
	/**
	 * 超时时间：5分钟
	 */
	private static final int CSRF_TOKEN_TIMEOUT = 5 * 60 * 1000; 
	/**
	 * CSRF token
	 */
	private String CSRFToken;
	/**
	 * CSRF token的生成时间
	 */
	private Date CSRFTokenDate;
	
	public LoginSessionCache(){
		loginState = false;
		checkCaptcha = false;
	}
	
	public void setLogin(Integer id,String username){
		this.id = id;
		this.username = username;
		loginState = true;
	}
	
	public boolean checkLogin(){
		return loginState;
	}
	
	/**
	 * 验证 登录验证码是否正确
	 * @param captcha
	 * @return
	 */
	public boolean checkLoginCaptcha(String captcha){
		if(loginCaptchaTime == null 
				|| loginCaptchaString == null
				|| captcha == null)
			return false;
		//
		if((new Date().getTime() - loginCaptchaTime.getTime()) > LOGIN_CAPTCHA_TIMEOUT){
			return false;
		}
		if(captcha.equalsIgnoreCase(loginCaptchaString))
			return true;
		else 
			return false;
	}
	
	/**
	 * 检查 CSRFtoken 是否有效
	 * @param token
	 * @return
	 */
	public boolean checkCSRFToken(String token){
		if(CSRFTokenDate == null)
			return false;
		if(token == null || token.equals(""))
			return false;
		if(CSRFToken == null || CSRFToken.equals(""))
			return false;
		if((new Date().getTime() - CSRFTokenDate.getTime()) > CSRF_TOKEN_TIMEOUT){
			return false;
		}
		if(!CSRFToken.equalsIgnoreCase(token))
			return false;
		return true;
	}
	
	public void saveLoginCaptcha(String captcha){
		checkCaptcha = true;
		this.loginCaptchaString = captcha;
		this.loginCaptchaTime = new Date();
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public Integer getId() {
		return id;
	}

	public boolean isCheckCaptcha() {
		return checkCaptcha;
	}

	public String getUsername() {
		return username;
	}

	/**
	 * 获取 CSRF随机字符串
	 * @return
	 */
	public String getCSRFToken() {
		CSRFTokenDate = new Date();
		CSRFToken = CommonUtil.getRandomStr(4);
		return CSRFToken;
	}
	
}
