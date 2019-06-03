package com.taotao.controller;

import com.content.service.TbContentService;
import com.taotao.pojo.TbContent;
import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TbContentController {
    @Autowired
    private TbContentService tbContentService;

    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUIResult getContentListByCategoryId(Long categoryId){
        return tbContentService.getContentByCategoryId(categoryId);
    }

    @RequestMapping("/content/save")
    @ResponseBody
    public TaotaoResult addContent(TbContent tbContent){
        return tbContentService.addTbContent(tbContent);
    }

    @RequestMapping("/content/delete")
    @ResponseBody
    public TaotaoResult deleteContent(String ids){
        return tbContentService.deleteContent(ids);
    }

    @RequestMapping("/rest/content/edit")
    @ResponseBody
    public TaotaoResult editContent(TbContent tbContent){
        return tbContentService.updateContent(tbContent);
    }
}
