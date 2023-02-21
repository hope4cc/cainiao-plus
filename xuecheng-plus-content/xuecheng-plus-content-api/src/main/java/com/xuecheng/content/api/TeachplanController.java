package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.service.TeachplanService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文件名：TeachplanController
 * 创建者：hope
 * 邮箱：1602774287@qq.com
 * 微信：hope4cc
 * 创建时间：2023/2/19-15:05
 * 描述：
 */
@Api(value = "课程计划管理相关的接口",tags = "课程计划管理相关的接口")
@RestController
public class TeachplanController {

    @Autowired
    private TeachplanService teachplanService;

    @GetMapping("/teachplan/{courserId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable Long courserId) {
        return teachplanService.findTeachplayTree(courserId);
    }

    @PostMapping("/teachplan")
    public void SaveTeachplan(@RequestBody SaveTeachplanDto dto) {
         teachplanService.saveTeachplan(dto);
    }
}