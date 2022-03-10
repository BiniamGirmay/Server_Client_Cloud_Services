package edu.miu.dnd;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import edu.miu.dnd.model.Employee;
import edu.miu.dnd.service.EmployeeServiceClient;

@EnableCircuitBreaker
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context =
                SpringApplication.run(Application.class, args);

        EmployeeServiceClient client = context.getBean(EmployeeServiceClient.class);

        List<Employee> employees = client.findAll();
        employees.forEach(e -> {
            System.out.println(client.findById(e.getId()));
        });
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
