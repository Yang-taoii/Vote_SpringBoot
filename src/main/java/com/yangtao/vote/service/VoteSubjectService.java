package com.yangtao.vote.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangtao.vote.entity.VoteSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangtao
 * @since 2021-03-22
 */
public interface VoteSubjectService extends IService<VoteSubject> {

    Page<Map<String, Object>> selectConditionPage(Page<Map<String, Object>> page, String vs_title);


    Page<Map<String, Object>> selectOptionAndTotal(Page<Map<String, Object>> page,String vs_title);
    Map<String, Object> selectOptionAndTotalById(Integer vs_id);
    List<Map<String, Object>> selectOptionsBySubjectId(Integer vs_id);
}
