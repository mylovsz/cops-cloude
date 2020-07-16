package com.cops.scada.service.impl;

import com.cops.scada.entity.UserCard;
import com.cops.scada.dao.UserCardDao;
import com.cops.scada.service.UserCardService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 工卡信息 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2020-04-08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserCardServiceImpl extends ServiceImpl<UserCardDao, UserCard> implements UserCardService {

}
