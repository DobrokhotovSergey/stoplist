package ua.varus.stoplist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ua.varus"})
public class SpringbootStoplistApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootStoplistApplication.class, args);
    }
}
