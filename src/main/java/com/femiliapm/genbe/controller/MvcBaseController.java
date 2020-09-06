package com.femiliapm.genbe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcBaseController {
	@GetMapping("/person-biodata")
	public String get() {
		return "dashboard/person-biodata";
	}

	@GetMapping("/pendidikan")
	public String getPend() {
		return "dashboard/pendidikan";
	}
}
