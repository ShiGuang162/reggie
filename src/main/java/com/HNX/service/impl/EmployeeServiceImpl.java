package com.HNX.service.impl;

import com.HNX.entity.Employee;
import com.HNX.mapper.EmployeeMapper;
import com.HNX.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
