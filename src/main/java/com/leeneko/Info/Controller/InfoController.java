package com.leeneko.Info.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leeneko.Info.Model.Project;
import com.leeneko.Info.Service.InfoService;
import com.leeneko.util.DateUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class InfoController {
	
	@Controller
	@RequestMapping("")
	class SwaggerRedirector {
		@GetMapping
		public String api() {
			return "redirect:/swagger-ui/index.html";
		}
	}
	
	
	
	@GetMapping("/info")
	public Object getInfo() {
		log.debug("/info start");
		
		Project proj = new InfoService().getInfo();
		
		log.info("return {}", proj.toString());
		
		return proj;
	}
	
	@GetMapping("/serverDate")
	public Object getServerDate() {
		return DateUtils.getDbDate();
	}
	
	@GetMapping("/serverDateTime")
	public Object getServerDateTime() {
		return DateUtils.getDbDateTime();
	}
	
}
