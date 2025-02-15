package com.homeworks.homework41.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ping")
public class PingController {

    @GetMapping
    private String ping(){
        return "OK";
    }
}
