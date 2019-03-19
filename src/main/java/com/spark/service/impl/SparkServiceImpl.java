package com.spark.service.impl;

import com.spark.bean.TransferInfo;
import com.spark.dao.TransferResitory;
import com.spark.service.SparkService;
import com.spark.service.SparrkSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SparkServiceImpl implements SparkService {
    @Autowired
    private TransferResitory transferResitory;
    @Override
    public boolean submitTaskToSpark(int jobid) {
        TransferInfo transfer=transferResitory.findById(jobid).get();
        if(transfer.getStatus().equalsIgnoreCase("1")){
            SparrkSql.invoke(transfer);
        }
        return false;
    }
}
