package com.cyc.bootcase.service;

import com.cyc.bootcase.bean.Employee;
import com.cyc.bootcase.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * 整合redis
 *   redis 是一个开源的bsd许可的，内存中的数据结构存储系统，他可以作为数据库，缓存和消息中间件
 *   1.安装redis，使用docker安装
 *   2.引入resdis场景启动器
 *   3.配置redis
 *   4.测试缓存
 *   原理CacheManager===cache缓存组件来实际给缓存中存取数据
 *     1.引入redis的starter，容器中就保存了的是RedisCacheManager
 *     2.RedisCacherManager 帮我们创建redisCahe来作为缓存逐渐
 *     3.默认保存数据k-v都是object，利用序列化保存
 *     3.默认保存数据k-v都是object，利用序列化保存
 */
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
