package com.mahendra.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient 
public class CouponserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponserviceApplication.class, args);
	}

}

/*
 * @RestController class ServiceInstanceRestController {
 * 
 * @Autowired private org.springframework.cloud.client.discovery.DiscoveryClient
 * discoveryClient;
 * 
 * @RequestMapping("/service-instance/{applicationName}") public
 * List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String
 * applicationName) { System.out.println(this.discoveryClient.description() +
 * " description"); System.out.println(this.discoveryClient.getServices() +
 * " list of services"); return
 * this.discoveryClient.getInstances(applicationName); } }
 */
