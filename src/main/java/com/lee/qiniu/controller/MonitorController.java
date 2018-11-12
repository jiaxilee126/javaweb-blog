package com.lee.qiniu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/monitor")
@Controller
public class MonitorController {
	
	@RequestMapping("/overview")
	public String overview() {
		return "/monitor";
	}
}
