package br.com.ifpe.rhcontrolapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class RhControlApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RhControlApiApplication.class, args);
    }

}
