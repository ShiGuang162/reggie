package com.HNX.service;

import com.HNX.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CategoryService extends IService<Category> {
   public void remove(Long ids);
}
