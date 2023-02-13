package com.example.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BankApplication {



    private static final Logger log = LoggerFactory.getLogger(BankApplication.class);

    public static void main(String[] args) {
        log.info("start main");
        SpringApplication.run(BankApplication.class, args);
        log.info("end main");
    }
}

