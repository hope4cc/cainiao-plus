package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.BindTeachplanMediaDto;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.TeachplanMedia;

import java.util.List;

/**
 * 文件名：TeachplanService
 * 创建者：hope
 * 邮箱：1602774287@qq.com
 * 微信：hope4cc
 * 创建时间：2023/2/19-15:38
 * 描述：
 */
public interface TeachplanService {
    /**
     * @description 查询课程计划树型结构
     * @param courseId  课程id
     * @return List<TeachplanDto>
     * @author Mr.M
     * @date 2022/9/9 11:13
     */
    public List<TeachplanDto> findTeachplanTree(long courseId);

    /**
     * 保存课程计划
     * @param dto
     */
    public void saveTeachplan(SaveTeachplanDto dto);


    /**
     * @description 教学计划绑定媒资
     * @param bindTeachplanMediaDto
     * @return com.xuecheng.content.model.po.TeachplanMedia
     * @author Mr.M
     * @date 2022/9/14 22:20
     */
    public TeachplanMedia associationMedia(BindTeachplanMediaDto bindTeachplanMediaDto);


}
