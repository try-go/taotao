package com.taotao.controller;

import com.content.service.TbContentCategoryService;
import com.taotao.result.EasyUICatTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TbContentCategoryController {
    @Autowired
    private TbContentCategoryService tbContentCategoryService;
    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUICatTree> categoryList(@RequestParam(value = "id",defaultValue = "0") long parentId){
        return tbContentCategoryService.getContentCategorys(parentId);
    }
}
