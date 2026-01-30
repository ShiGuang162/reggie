package com.HNX.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.HNX.entity.ShoppingCart;
import com.HNX.mapper.ShoppingCartMapper;
import com.HNX.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
