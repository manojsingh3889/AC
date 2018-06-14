package org.crealytics;

import org.crealytics.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.crealytics")
public class BootStrap {

    @Autowired
    AdRepository repo;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootStrap.class, args);


    }
}
