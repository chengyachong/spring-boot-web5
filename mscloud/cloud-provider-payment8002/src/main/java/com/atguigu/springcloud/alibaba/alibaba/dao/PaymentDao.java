package com.atguigu.springcloud.alibaba.alibaba.dao;

import com.atguigu.springcloud.alibaba.alibaba.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface  PaymentDao {
    public  int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
