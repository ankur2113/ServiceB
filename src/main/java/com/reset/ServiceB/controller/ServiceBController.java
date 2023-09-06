package com.reset.ServiceB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reset.ServiceB.feignConfig.ServiceAClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api")
public class ServiceBController {

	@Autowired
	private ServiceAClient serviceAClient;
	
	
	@GetMapping("/get-greeting")
	@CircuitBreaker(name = "service-a", fallbackMethod = "fallbackForServiceA")
	public ResponseEntity<String> getGreetingFromServiceA() {
	    return ResponseEntity.ok(serviceAClient.greet());
	}
	
	public ResponseEntity<String> fallbackForServiceA(Exception e) {
        return ResponseEntity.ok("Service A is currently unavailable. Please try again later.");
    }
}
