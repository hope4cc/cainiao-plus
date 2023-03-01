package com.xuecheng.content.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 文件名：FreemarkerController
 * 创建者：hope
 * 邮箱：1602774287@qq.com
 * 微信：hope4cc
 * 创建时间：2023/2/28-14:39
 * 描述：
 */
@Controller
public class FreemarkerController {
    @GetMapping("/testfreemarker")
    public ModelAndView test() {

        ModelAndView modelAndView = new ModelAndView();
        //准备模型数据
        modelAndView.addObject("name","小明");
        //设置视图的名称，就是模板文件的名称(去掉扩展名)
        modelAndView.setViewName("test");

        return modelAndView;

    }
}
