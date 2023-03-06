package br.com.stapassoli.msserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsServerApplication.class, args);
    }

}
