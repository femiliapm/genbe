package com.femiliapm.genbe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcBaseController {
	@GetMapping("/person-biodata")
	public String index() {
		return "dashboard/index";
	}

	@GetMapping("/pendidikan")
	public String index2() {
		return "dashboard/index2";
	}
}
