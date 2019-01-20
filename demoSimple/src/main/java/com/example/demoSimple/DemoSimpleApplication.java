package com.example.demoSimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;


@RestController
@SpringBootApplication
public class DemoSimpleApplication {

    /*
    * Return bytes received from request as String
    *
    * */

    @RequestMapping(value = "/api/image", method = RequestMethod.POST)
    public String imageApi(@RequestBody byte[] bytes) {

        String returnString = new String(bytes, Charset.forName("UTF-8"));
        return returnString;
    }

    public static void main(java.lang.String[] args) {
        SpringApplication.run(DemoSimpleApplication.class, args);
    }

}

