package com.HNX.service.impl;

import com.HNX.entity.User;
import com.HNX.mapper.UserMapper;
import com.HNX.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
