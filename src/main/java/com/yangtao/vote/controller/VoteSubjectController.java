package com.yangtao.vote.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangtao.vote.entity.VoteItem;
import com.yangtao.vote.entity.VoteOption;
import com.yangtao.vote.entity.VoteSubject;
import com.yangtao.vote.service.VoteItemService;
import com.yangtao.vote.service.VoteOptionService;
import com.yangtao.vote.service.VoteSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
@Slf4j
@Controller
@RequestMapping("/voteSubject")
public class VoteSubjectController {

    @Autowired
    VoteSubjectService subjectService;
    @Autowired
    VoteOptionService optionService;
    @Autowired
    VoteItemService itemService;

    @RequestMapping("/page")
    public String voteSubjectPage(Integer current,String vs_title, Model model){
        if (current==null){
            current=1;
        }
        int size = 3;
        Page<Map<String, Object>> page = new Page<>(current,size);
        Page<Map<String, Object>> maps = subjectService.selectOptionAndTotal(page,vs_title);
        model.addAttribute("pages",maps.getPages());
        List<Map<String, Object>> records = page.getRecords();
        model.addAttribute("subjects",records);
        return "vote/votelist";
    }

    @RequestMapping("/getById")
    public String getOne(Integer vs_id,Model model){
        Map<String, Object> map = subjectService.selectOptionAndTotalById(vs_id);
        List<Map<String, Object>> options = subjectService.selectOptionsBySubjectId(vs_id);
        model.addAttribute("getById",map);
        model.addAttribute("options",options);
        return "vote/voteview";
    }

    @RequestMapping("/add.html")
    public String toAdd(){
        return "vote/add";
    }
    @RequestMapping("/add")
    public String addSubject(String[] vo_option,@RequestParam Map<String, Object> maps){
        System.out.println(maps+"===========================================");
        String vs_title = (String)maps.get("vs_title");
        String vs_type = (String) maps.get("vs_type");
        VoteSubject voteSubject = new VoteSubject(vs_title,vs_type);
        boolean save = subjectService.save(voteSubject);
        log.info("是否成功：{}",save);
        VoteOption voteOption = new VoteOption();

        QueryWrapper<VoteSubject> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("vs_title",vs_title);
        map.put("vs_type",vs_type);
        queryWrapper.allEq(map);
        VoteSubject one = subjectService.getOne(queryWrapper);
        System.out.println("VoteSubject--------------"+one);

        for (int i = 0; i < vo_option.length; i++) {
            String s = vo_option[i];
            System.out.println(s+"------------------------------------------");
            voteOption.setVoOrder(i+1);
            voteOption.setVoOption(s);
            voteOption.setVsId(one.getVsId());
            boolean save1 = optionService.save(voteOption);
            log.info("是否成功：{}",save1);
        }
        return "forward:/voteSubject/page";
    }


    /**
     * 进入维护页面
     * @param current 当前页
     * @param model model
     * @return 维护页面
     */
    @RequestMapping("/maintain.html")
    public String toModify(Integer current, String vs_title,Model model){
        if (current==null){
            current=1;
        }
        int size = 3;
        Page<Map<String, Object>> page = new Page<>(current,size);
        Page<Map<String, Object>> maps = subjectService.selectOptionAndTotal(page,vs_title);
        model.addAttribute("pages",maps.getPages());
        List<Map<String, Object>> records = page.getRecords();
        model.addAttribute("subjects",records);
        return "vote/maintain";
    }
    @Transactional
    @RequestMapping("/del")
    @ResponseBody
    public boolean del(Integer vs_id){
        QueryWrapper<VoteItem> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("vs_id",vs_id);
        itemService.remove(wrapper1);

        QueryWrapper<VoteOption> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("vs_id",vs_id);
        optionService.remove(wrapper2);

        boolean b3 = subjectService.removeById(vs_id);
        return  b3;
    }




















    @RequestMapping("/vote.html")
    public String toVote(Integer vs_id,Model model){
        Map<String, Object> map = subjectService.selectOptionAndTotalById(vs_id);
        List<Map<String, Object>> options = subjectService.selectOptionsBySubjectId(vs_id);
        model.addAttribute("ss",map);
        model.addAttribute("options",options);
        return "vote/vote";
    }


}

