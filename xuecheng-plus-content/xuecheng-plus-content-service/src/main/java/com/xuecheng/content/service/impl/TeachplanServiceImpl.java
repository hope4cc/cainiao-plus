package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件名：TeachplanServiceImpl
 * 创建者：hope
 * 邮箱：1602774287@qq.com
 * 微信：hope4cc
 * 创建时间：2023/2/19-15:38
 * 描述：
 */
@Slf4j
@Service
public class TeachplanServiceImpl implements TeachplanService {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Override
    public List<TeachplanDto> findTeachplayTree(long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    /**
     *     新增、修改
     *     判断 带不带id，带id修改，不带新增
     */
    @Override
    public void saveTeachplan(SaveTeachplanDto dto) {
        Long id = dto.getId();
        Teachplan teachplan = teachplanMapper.selectById(id);
        if (teachplan == null){
            teachplan = new Teachplan();
            BeanUtils.copyProperties(dto, teachplan);
            //找到同级课程计划的数量
            int count = getTeachplanCount(dto.getCourseId(), dto.getParentid());
            //新课程的值
            teachplan.setOrderby(count+1);
            //计算 默认顺序 orderby
            teachplanMapper.insert(teachplan);
        }else {
            //更新
            BeanUtils.copyProperties(dto, teachplan);
            teachplanMapper.updateById(teachplan);
        }
    }

    //计算新的课程计划orderby 找到同级课程计划的数量
    //SELECT count(1)from teachplan where course id=117 and parentid=268
    /**
     * @description 获取最新的排序号
     * @param courseId  课程id
     * @param parentId  父课程计划id
     * @return int 最新排序号
     * @date 2022/9/9 13:43
     */
    private int getTeachplanCount(long courseId,long parentId){
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId,courseId);
        queryWrapper.eq(Teachplan::getParentid,parentId);
        Integer count = teachplanMapper.selectCount(queryWrapper);
        return count;
    }
}
