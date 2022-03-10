package edu.miu.dnd.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import edu.miu.dnd.model.Employee;

@Service
public class EmployeeServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring-boot-server.name}")
    private String serverName;

    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public Employee findById(Long employeeCode) {
        String url = getBaseServiceUrl() + "/employees/" + employeeCode;
        return restTemplate.getForObject(url, Employee.class);
    }

    public Employee findByIdFallback(String EmployeeCode) {
        System.out.println("Inside findByIdFallback()");
        return new Employee();
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public List<Employee> findAll() {
        Employee[] employees = restTemplate.getForObject(getBaseServiceUrl() + "/employees", Employee[].class);
        return Arrays.asList(employees);
    }

    public List<Employee> findAllFallback() {
        System.out.println("Inside findAllFallback()");
        return new ArrayList<>();
    }

    private String getBaseServiceUrl() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serverName);
        serviceInstances.forEach(System.out::println);
        return serviceInstances.get(0).getUri().toString();
    }

}
