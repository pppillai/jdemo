package com.example.demoSimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;


@RestController
@SpringBootApplication
public class DemoSimpleApplication {

    @RequestMapping(value = "/api/image", method = RequestMethod.POST)
    public String imageApi(@RequestBody byte[] bytes) {

        String returnString = new String(bytes, Charset.forName("UTF-8"));
        return returnString;
    }

    public static void main(java.lang.String[] args) {
        SpringApplication.run(DemoSimpleApplication.class, args);
    }

}

