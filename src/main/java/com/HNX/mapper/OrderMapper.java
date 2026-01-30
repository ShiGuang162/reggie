package com.HNX.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.HNX.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}