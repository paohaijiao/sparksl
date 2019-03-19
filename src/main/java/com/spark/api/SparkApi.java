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
        System.out.println("###################################开始同步");
        System.out.println("##########################################");
        System.out.println("########################################");
        long start=System.currentTimeMillis();
        for(int i=1;i<=126;i++){
            try{
                sparkService.submitTaskToSpark(i);
            }catch (Exception e){
                System.out.println("这次出错的原因是"+e);
                continue;
            }

        }
        long end=System.currentTimeMillis();
        System.out.println("这次同步花费了"+(end-start));

       return "init";
    }
}
