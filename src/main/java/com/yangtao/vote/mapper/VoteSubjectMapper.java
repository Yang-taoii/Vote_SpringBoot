package com.yangtao.vote.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangtao.vote.entity.VoteSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangtao
 * @since 2021-03-22
 */
@Repository
public interface VoteSubjectMapper extends BaseMapper<VoteSubject> {

    Page<Map<String, Object>> selectConditionPage(Page<Map<String, Object>> page, @Param("vs_title") String vs_title);

    Page<Map<String, Object>> selectOptionAndTotal(Page<Map<String, Object>> page,@Param("vs_title") String vs_title);

    Map<String, Object> selectOptionAndTotalById(Integer vs_id);

    List<Map<String, Object>> selectOptionsBySubjectId(Integer vs_id);


}
