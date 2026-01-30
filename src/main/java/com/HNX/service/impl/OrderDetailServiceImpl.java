package com.HNX.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.HNX.entity.OrderDetail;
import com.HNX.mapper.OrderDetailMapper;
import com.HNX.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}