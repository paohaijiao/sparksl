package com.spark.service.impl;

import com.spark.bean.TransferInfo;
import com.spark.dao.TransferResitory;
import com.spark.service.SparkService;
import com.spark.service.SparrkSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SparkServiceImpl implements SparkService {
    @Autowired
    private TransferResitory transferResitory;
    @Override
    public String  submitTaskToSpark(int jobid) {
        TransferInfo transfer=transferResitory.findByIdAndStatus(jobid,"1");
        if(null==transfer){
            return "没有查询到相关job信息";
        }else{
            SparrkSql.invoke(transfer);
            return "Spark执行成功了";
        }

    }
}
