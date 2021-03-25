package com.yangtao.vote.service;

import com.yangtao.vote.entity.VoteUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangtao
 * @since 2021-03-22
 */
public interface VoteUserService extends IService<VoteUser> {
    VoteUser selectUserByName(String name);
}
