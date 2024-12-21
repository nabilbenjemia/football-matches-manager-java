package Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"Controller", "Model"})
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
        System.out.println("Application is running");
    }


}
