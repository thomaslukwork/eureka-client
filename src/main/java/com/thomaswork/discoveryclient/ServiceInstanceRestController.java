package com.thomaswork.discoveryclient;

import com.netflix.discovery.EurekaClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceInstanceRestController {

  @Autowired
  private DiscoveryClient discoveryClient;

  @Autowired
  @Lazy
  private EurekaClient eurekaClient;

  @Value("${spring.application.name}")
  private String appName;

  @Value("${server.port}")
  private String portNumber;

  @RequestMapping("/service-instances/{applicationName}")
  public List<ServiceInstance> serviceInstancesByApplicationName(
      @PathVariable String applicationName) {
    return this.discoveryClient.getInstances(applicationName);
  }

  @RequestMapping("/greeting")
  public String greeting() {
    System.out.println("Request received on port number " + portNumber);
    return String
        .format("Hello from '%s with Port Number %s'!", eurekaClient.getApplication(appName)
            .getName(), portNumber);
  }
}

