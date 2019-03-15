package com.spark.api;

import com.spark.service.SparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class SparkApi {
    @Autowired
    private SparkService sparkService;
    @GetMapping("init")
    public String init(){
        sparkService.submitTaskToSpark(1);
       return "init";
    }
}
