package com.tanbo.srb.core.service.impl;

import com.tanbo.srb.core.pojo.entity.UserInfo;
import com.tanbo.srb.core.mapper.UserInfoMapper;
import com.tanbo.srb.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author tanbo
 * @since 2021-10-17
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
