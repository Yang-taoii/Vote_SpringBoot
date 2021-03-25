package com.yangtao.vote.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author：杨涛
 * @create: 2021-03-05 09:45
 * FieldFill.INSERT / FieldFill.INSERT_UPDATE 自动赋值的处理器
 */

@Slf4j
@Component
@MapperScan("com.yangtao.springboot.mapper")
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //插入时的填充策略
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新时的填充策略
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
