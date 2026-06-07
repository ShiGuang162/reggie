package com.HNX.controller;

import com.HNX.common.BaseContext;
import com.HNX.common.CustomException;
import com.HNX.common.R;
import com.HNX.entity.OrderDetail;
import com.HNX.entity.ShoppingCart;
import com.HNX.entity.Orders;
import com.HNX.service.OrderDetailService;
import com.HNX.service.OrderService;
import com.HNX.service.ShoppingCartService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("订单数据：{}",orders);
        orderService.submit(orders);
        return R.success("下单成功");
    }

    /**
     * 订单分页查询（管理端）
     * @param page
     * @param pageSize
     * @param number 查询订单号
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, Long number, String beginTime, String endTime) {
        log.debug("订单分页查询: page={}, pageSize={}, number={}, beginTime={}, endTime={}", page, pageSize, number, beginTime, endTime);
        return orderService.pageQuery(page, pageSize, number, beginTime, endTime);
    }

    /**
     * 更新订单状态（取消、派送、完成）
     * @param orders
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Orders orders) {
        log.info("更新订单状态: {}", orders);
        orderService.updateById(orders);
        return R.success("订单状态更新成功");
    }

    /**
     * 用户订单分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/userPage")
    public R<Page> userPage(int page, int pageSize) {
        Long userId = BaseContext.getCurrentId();
        log.debug("用户订单分页查询: userId={}, page={}, pageSize={}", userId, page, pageSize);
        return orderService.userPageQuery(page, pageSize, userId);
    }

    /**
     * 查询用户所有订单
     * @return
     */
    @GetMapping("/list")
    public R<List<Orders>> list() {
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUserId, userId);
        queryWrapper.orderByDesc(Orders::getOrderTime);
        List<Orders> list = orderService.list(queryWrapper);
        return R.success(list);
    }

    /**
     * 再来一单
     * @param orderId 原订单ID
     * @return
     */
    @PostMapping("/again")
    public R<String> again(@RequestBody Long orderId) {
        log.info("再来一单: orderId={}", orderId);

        // 查询原订单的明细
        LambdaQueryWrapper<OrderDetail> detailWrapper = new LambdaQueryWrapper<>();
        detailWrapper.eq(OrderDetail::getOrderId, orderId);
        List<OrderDetail> orderDetails = orderDetailService.list(detailWrapper);

        if (orderDetails == null || orderDetails.isEmpty()) {
            throw new CustomException("订单无明细，无法再来一单");
        }

        // 获取当前用户ID
        Long userId = BaseContext.getCurrentId();

        // 清空当前用户购物车
        LambdaQueryWrapper<ShoppingCart> cartWrapper = new LambdaQueryWrapper<>();
        cartWrapper.eq(ShoppingCart::getUserId, userId);
        shoppingCartService.remove(cartWrapper);

        // 将订单明细添加到购物车
        List<ShoppingCart> carts = orderDetails.stream().map(item -> {
            ShoppingCart cart = new ShoppingCart();
            cart.setUserId(userId);
            cart.setDishId(item.getDishId());
            cart.setSetmealId(item.getSetmealId());
            cart.setName(item.getName());
            cart.setImage(item.getImage());
            cart.setNumber(item.getNumber());
            cart.setAmount(item.getAmount());
            cart.setCreateTime(LocalDateTime.now());
            return cart;
        }).collect(Collectors.toList());

        shoppingCartService.saveBatch(carts);
        return R.success("再来一单成功");
    }
}