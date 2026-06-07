package com.HNX.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.HNX.common.R;
import com.HNX.entity.Orders;

public interface OrderService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);

    /**
     * 订单分页查询（管理端）
     */
    R<Page> pageQuery(int page, int pageSize, Long number, String beginTime, String endTime);

    /**
     * 用户订单分页查询
     */
    R<Page> userPageQuery(int page, int pageSize, Long userId);
}
