package com.spark.dao;

import com.spark.bean.TransferInfo;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TransferResitory extends JpaRepository<TransferInfo,Integer> {

}
