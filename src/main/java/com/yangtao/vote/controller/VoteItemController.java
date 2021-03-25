package com.yangtao.vote.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangtao.vote.entity.VoteItem;
import com.yangtao.vote.service.VoteItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangtao
 * @since 2021-03-22
 */
@Slf4j
@Controller
@RequestMapping("/voteItem")
public class VoteItemController {

    @Autowired
    VoteItemService itemService;


    @RequestMapping("/vote")
    public String vote(Integer[] options,Integer vs_id,Integer vu_user_id){

        for (int i = 0; i < options.length; i++) {
            Integer option = options[i];
            VoteItem voteItem = new VoteItem();
            voteItem.setVoId(option);
            System.out.println("options-------------------------"+option);
            voteItem.setVuUserId(vu_user_id);
            voteItem.setVsId(vs_id);
            boolean save = itemService.save(voteItem);
            log.info("是否添加成功--------> {}",save);
        }
        System.out.println("-vs_id------------------------"+vs_id);
        System.out.println("--vu_user_id------------------------"+vu_user_id);
        return "forward:/voteSubject/page";
    }


    @RequestMapping("/regVote")
    @ResponseBody
    public boolean regVote(Integer vu_user_id,Integer vs_id) throws TooManyResultsException {
        QueryWrapper<VoteItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vu_user_id",vu_user_id);
        queryWrapper.eq("vs_id",vs_id);
        List<VoteItem> list = itemService.list(queryWrapper);
        if (list==null){
            return true;
        }
        return false;
    }

}

