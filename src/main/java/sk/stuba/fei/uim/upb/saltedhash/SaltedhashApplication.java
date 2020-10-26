package sk.stuba.fei.uim.upb.saltedhash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EntityScan(basePackages = {"sk.stuba.fei.uim.upb.saltedhash"})
public class SaltedhashApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaltedhashApplication.class, args);

    }

}
