package com.reset.ServiceB.feignConfig;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-a", url = "http://localhost:7071/api")
public interface ServiceAClient {
	
	@GetMapping("/greet")
	String greet();
}
