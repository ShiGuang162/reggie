package com.HNX.service;

import com.HNX.entity.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ShoppingCartService extends IService<ShoppingCart> {

    /**
     * 原子递增商品数量（解决并发问题）
     * @param id 购物车ID
     */
    void incrementNumber(Long id);

    /**
     * 原子递减商品数量（仅当数量大于 1 时更新）
     * @return 受影响行数，0 表示当前数量为 1 需走删除逻辑
     */
    int decrementNumber(Long id);
}
