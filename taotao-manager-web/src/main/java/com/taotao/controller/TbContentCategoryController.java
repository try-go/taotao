package com.taotao.controller;

import com.content.service.TbContentCategoryService;
import com.taotao.result.EasyUICatTree;
import com.taotao.result.TaotaoResult;
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

    @RequestMapping("/content/category/create")
    @ResponseBody
    public TaotaoResult categoryCreate(Long parentId,String name){
        return tbContentCategoryService.tbContentCategoryCreate(parentId,name);
    }

    @RequestMapping("/content/category/update")
    public void categoryUpdateName(Long id,String name){
        tbContentCategoryService.tbContentCategoryUpdateName(id,name);
    }

    @RequestMapping("/content/category/delete")
    @ResponseBody
    public TaotaoResult categoryDelete(Long parentId,Long id){
        tbContentCategoryService.tbContentCategoryDelete(parentId,id);
        return TaotaoResult.ok();
    }
}
