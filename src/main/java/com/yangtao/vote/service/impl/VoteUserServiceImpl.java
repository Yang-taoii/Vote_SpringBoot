package com.yangtao.vote.service.impl;

import com.yangtao.vote.entity.VoteUser;
import com.yangtao.vote.mapper.VoteUserMapper;
import com.yangtao.vote.service.VoteUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangtao
 * @since 2021-03-22
 */
@Service
public class VoteUserServiceImpl extends ServiceImpl<VoteUserMapper, VoteUser> implements VoteUserService {

    @Autowired
    VoteUserMapper voteUserMapper;


    @Override
    public VoteUser selectUserByName(String name) {
        return voteUserMapper.selectUserByName(name);
    }
}
