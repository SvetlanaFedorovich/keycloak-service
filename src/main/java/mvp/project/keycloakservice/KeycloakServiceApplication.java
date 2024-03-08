package mvp.project.keycloakservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class KeycloakServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeycloakServiceApplication.class, args);
    }

}
