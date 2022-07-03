package com.service.payroll.JSP;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JSPController {
	
	@RequestMapping("/jspView")
	String showJSPTemplate() {
		return "custom-jsp-template";
	}

}
