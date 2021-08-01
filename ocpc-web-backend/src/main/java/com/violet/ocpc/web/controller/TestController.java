package com.violet.ocpc.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HYJ
 *
 */
@RestController
@RequestMapping("/api/test")
public class TestController {
    @PostMapping(value = "/testEmail")
    public String test01(String email) {
	System.out.println(">>>> emial : " + email);
	
	return "xxx";
    }
}
