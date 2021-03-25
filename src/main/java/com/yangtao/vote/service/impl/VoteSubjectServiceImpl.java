package com.yangtao.vote.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangtao.vote.entity.VoteSubject;
import com.yangtao.vote.mapper.VoteSubjectMapper;
import com.yangtao.vote.service.VoteSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangtao
 * @since 2021-03-22
 */
@Service
public class VoteSubjectServiceImpl extends ServiceImpl<VoteSubjectMapper, VoteSubject> implements VoteSubjectService {

    @Autowired
    VoteSubjectMapper voteSubjectMapper;


    @Transactional(readOnly = true)
    @Override
    public Page<Map<String, Object>> selectConditionPage(Page<Map<String, Object>> page,String vs_title ) {
        return voteSubjectMapper.selectConditionPage(page,vs_title);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Map<String, Object>> selectOptionAndTotal(Page<Map<String, Object>> page,String vs_title) {
        return voteSubjectMapper.selectOptionAndTotal(page,vs_title);
    }

    @Override
    public Map<String, Object> selectOptionAndTotalById(Integer vs_id) {
        return voteSubjectMapper.selectOptionAndTotalById(vs_id);
    }

    @Override
    public List<Map<String, Object>> selectOptionsBySubjectId(Integer vs_id) {
        return voteSubjectMapper.selectOptionsBySubjectId(vs_id);
    }


}
