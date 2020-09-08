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

	@GetMapping("/person-modal")
	public String index3() {
		return "dashboard/index3";
	}

	@GetMapping("/person-modal/get-by-nik")
	public String index4() {
		return "dashboard/index4";
	}

	@GetMapping("/pendidikan-modal")
	public String index5() {
		return "dashboard/index5";
	}
}
