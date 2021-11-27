package com.cyc.bootcase.service;

import com.cyc.bootcase.bean.Employee;
import com.cyc.bootcase.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的返回结果保存在缓存中
     * 以后再要相同的数据 直接从缓存中获取不用再调用方法了
     * 几个属性：
     *    cacheNames/value 执行缓存的名字
     *    key 要缓存数据使用的key 可以用他来指定，默认使用方法参数的值，方法的返回值 编写spel
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"emp"})
    public Employee getEmployee(Integer id){
        System.out.println("查询"+id);
       return  employeeMapper.getEmpById(id);
    }

    /**
     * @cachePut 即调用方法，又更新缓存数据
     * @param employee
     * @return
     *
     * 运行时机：先调用目标方法 然后将目标方法的结果缓存起来
     */
    @CachePut(value = "emp")
    public Employee updateEmp(Employee employee){
        System.out.println("员工更信息"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.id"),
                    @CachePut(value = "emp",key = "#result.email")
            }

    )
    public Employee getEmpByLastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }

}
