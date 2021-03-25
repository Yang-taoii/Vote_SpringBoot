package com.yangtao.vote.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class VoteSubject implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 自身id
     */
      @TableId(value = "vs_id", type = IdType.AUTO)
      private Integer vsId;

      /**
     * 主题内容
     */
      private String vsTitle;

      /**
     * 选项类型1.单选 2.多选
     */
      private String vsType;

    public VoteSubject(String vsTitle, String vsType) {
      this.vsTitle = vsTitle;
      this.vsType = vsType;
    }
}
