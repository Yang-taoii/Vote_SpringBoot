package com.yangtao.vote.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangtao.vote.entity.VoteItem;
import com.yangtao.vote.entity.VoteOption;
import com.yangtao.vote.entity.VoteSubject;
import com.yangtao.vote.service.VoteItemService;
import com.yangtao.vote.service.VoteOptionService;
import com.yangtao.vote.service.VoteSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangtao
 * @since 2021-03-22
 */
@Controller
@RequestMapping("/voteOption")
public class VoteOptionController {

    @Autowired
    VoteOptionService optionService;
    @Autowired
    VoteSubjectService subjectService;
    @Autowired
    VoteItemService itemService;


    /**
     * 进入修改页面
     * @return 修改页面
     */
    @RequestMapping("modify.html")
    public String toModify(Integer vs_id, Model model){
        List<Map<String, Object>> options = subjectService.selectOptionsBySubjectId(vs_id);
        Map<String, Object> map = subjectService.selectOptionAndTotalById(vs_id);
        model.addAttribute("options",options);
        model.addAttribute("map",map);
        return "vote/modify";
    }


    @Transactional
    @RequestMapping("/modify")
    public String modify(String[] vo_option,Integer[] vo_id,String vs_title,String vs_type,Integer vs_id){
        int l1= vo_option.length;//表单提交过来的个数  4
        int l2= vo_id.length;//没被删除的个数 2
        int l3 = l1-l2;  //2
        System.out.println("表单提交过来的个数："+l1);
        System.out.println("没被删除的个数："+l2);
        System.out.println(l3);

        for (int i = 0; i <l2 ; i++) {
            String option =  vo_option[i];
            Integer id = vo_id[i];
            VoteOption voteOption = new VoteOption();
            voteOption.setVoId(id);
            voteOption.setVoOption(option);
            boolean b = optionService.updateById(voteOption);
            System.out.println("没被删除的选项是否修改成功？"+b);
        }
        QueryWrapper<VoteOption> queryWrapper = new QueryWrapper<>();
        queryWrapper.notIn("vo_id", Arrays.asList(vo_id));
        queryWrapper.eq("vs_id",vs_id);
        List<VoteOption> list = optionService.list(queryWrapper);


        for (int i = 0; i <list.size(); i++) {
            QueryWrapper<VoteItem> queryWrapper1 = new QueryWrapper<>();
            int voId = list.get(i).getVoId();
            System.out.println("需要删除的id "+voId);
            queryWrapper1.eq("vo_id",voId);
            boolean remove = itemService.remove(queryWrapper1);
            System.out.println("itemService是否删除成功？"+remove);
            boolean b = optionService.removeById(voId);
            System.out.println("需要删除的id是否删除成功？"+b);
        }

        for (int i = l2; i <l1 ; i++) {
            VoteOption voteOption = new VoteOption();
            voteOption.setVoOrder(i+1);
            voteOption.setVsId(vs_id);
            String order = vo_option[i];
            voteOption.setVoOption(order);
            boolean save = optionService.save(voteOption);
            System.out.println("新增的选项是否新增成功？"+save);
        }

        VoteSubject voteSubject = new VoteSubject();
        voteSubject.setVsType(vs_type);
        voteSubject.setVsTitle(vs_title);
        voteSubject.setVsId(vs_id);
        boolean b = subjectService.updateById(voteSubject);
        System.out.println("标题与类型是否修改成功？"+b);
        return "forward:/voteSubject/maintain.html";
    }


}

