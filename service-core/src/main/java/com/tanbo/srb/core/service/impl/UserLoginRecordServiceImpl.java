package com.tanbo.srb.core.service.impl;

import com.tanbo.srb.core.pojo.entity.UserLoginRecord;
import com.tanbo.srb.core.mapper.UserLoginRecordMapper;
import com.tanbo.srb.core.service.UserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author tanbo
 * @since 2021-10-17
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

}
