package com.yangtao.vote.mapper;

import com.yangtao.vote.entity.VoteUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangtao
 * @since 2021-03-22
 */

@Repository
public interface VoteUserMapper extends BaseMapper<VoteUser> {

   VoteUser selectUserByName(@Param("vu_user_name") String name);
}
