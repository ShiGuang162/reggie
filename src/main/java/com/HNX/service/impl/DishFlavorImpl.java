package com.HNX.service.impl;

import com.HNX.entity.DishFlavor;
import com.HNX.mapper.DishFlavorMapper;
import com.HNX.service.DishFlavorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
