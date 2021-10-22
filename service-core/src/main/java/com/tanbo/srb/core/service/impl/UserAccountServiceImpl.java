package com.tanbo.srb.core.service.impl;

import com.tanbo.srb.core.pojo.entity.UserAccount;
import com.tanbo.srb.core.mapper.UserAccountMapper;
import com.tanbo.srb.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author tanbo
 * @since 2021-10-17
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
