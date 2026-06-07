package com.HNX.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.HNX.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

    /**
     * 原子递增商品数量
     * @param id 购物车ID
     */
    @Update("UPDATE shopping_cart SET number = number + 1 WHERE id = #{id}")
    void incrementNumber(Long id);

    /**
     * 原子递减商品数量（仅当数量大于 1 时更新）
     * @return 受影响行数
     */
    @Update("UPDATE shopping_cart SET number = number - 1 WHERE id = #{id} AND number > 1")
    int decrementNumber(Long id);
}
