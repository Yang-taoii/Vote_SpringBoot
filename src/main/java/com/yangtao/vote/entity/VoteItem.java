package com.yangtao.vote.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class VoteItem implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 自身id
     */
        @TableId(value = "vi_id", type = IdType.AUTO)
      private Long viId;
      /**
     * 用户id
     */
      private Integer vuUserId;

      /**
     * 主题id
     */
      private Integer vsId;

      /**
     * 选项id
     */
      private Integer voId;

  public VoteItem(Integer vuUserId, Integer vsId, Integer voId) {
    this.vuUserId = vuUserId;
    this.vsId = vsId;
    this.voId = voId;
  }
}
