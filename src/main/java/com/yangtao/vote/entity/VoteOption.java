package com.yangtao.vote.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangtao
 * @since 2021-03-22
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class VoteOption implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 自身id
     */
        @TableId(value = "vo_id", type = IdType.AUTO)
      private Integer voId;

      /**
     * 主题id
     */
      private Integer vsId;

      /**
     * 选项内容
     */
      private String voOption;

      /**
     * 该项目获得的票数
     */
      private Integer voOrder;


}
