package com.cyc.bootcase;

import com.cyc.bootcase.bean.Employee;
import com.cyc.bootcase.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringBootCaseApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;//操作k-v
    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作字符串的
    @Autowired
     EmployeeMapper employeeMapper;

    /**
     * string list set hash zset
     */
    @Test
    void testRedisTem(){
       // stringRedisTemplate.opsForList().leftPush("mylist","1");
       Employee employee =  employeeMapper.getEmpById(1);
          redisTemplate.opsForValue().set("emp-1",employee);
    }

    @Test
    void contextLoads() {
      Employee employee =  employeeMapper.getEmpById(1);
        System.out.println(employee);
    }


}
