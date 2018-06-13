package org.crealytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class BootStrap {

    @Autowired
    AdDetailRepo repo;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootStrap.class, args);


    }
}
