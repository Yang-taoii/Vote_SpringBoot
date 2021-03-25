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
public class VoteUser implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 自身id
     */
      @TableId(value = "vu_user_id", type = IdType.AUTO)
      private Long vuUserId;

      /**
     * 用户名
     */
      private String vuUserName;

      /**
     * 用户密码
     */
      private String vuPassword;

      /**
     * 用户角色，1.管理员 2.普通用户
     */
      private Integer vuStatus;

      private String perms; //用户权限


}
