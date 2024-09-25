package com.example.Actuator.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class actuatorController {

    @GetMapping("/data")
    public String hello(){
        return " hii i am in actuator";
    }

}
