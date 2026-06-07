package com.HNX.controller;

import com.HNX.common.R;
import com.HNX.entity.OrderDetail;
import com.HNX.service.OrderDetailService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单明细
 */
@Slf4j
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * 根据订单ID查询订单明细
     * @param id 订单ID
     * @return 订单明细列表
     */
    @GetMapping("/{id}")
    public R<List<OrderDetail>> getByOrderId(@PathVariable Long id) {
        log.info("查询订单明细, orderId={}", id);
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOrderId, id);
        List<OrderDetail> list = orderDetailService.list(queryWrapper);
        return R.success(list);
    }
}