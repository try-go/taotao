package com.taotao.controller;

import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/item/param/list")
    @ResponseBody
    public EasyUIResult findItemParams(Integer page,Integer rows){
        page = page==null?1:page;
        Integer pageSize = rows==null?30:rows;
        return itemParamService.getItemParams(page,pageSize);
    }

    @RequestMapping("/item/param/query/itemcatid/{itemcatid}")
    @ResponseBody
    public TaotaoResult paramQuery(@PathVariable Long itemcatid){
        return itemParamService.findItemParam(itemcatid);
    }

    @RequestMapping("/item/param/save/{cid}")
    @ResponseBody
    public TaotaoResult addParam(@PathVariable Long cid,String paramData){
        return itemParamService.addItemParam(cid,paramData);
    }

    @RequestMapping("/item/param/delete")
    @ResponseBody
    public TaotaoResult deleteParam(String ids){
        return itemParamService.deleteParam(ids);
    }

}
