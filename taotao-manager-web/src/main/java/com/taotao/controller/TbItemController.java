package com.taotao.controller;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TbItemController {
    @Autowired
    private TbItemService itemService;

    @RequestMapping("/item/{id}")
    @ResponseBody
    public TbItem findTbItem(@PathVariable long id){
        return itemService.findTbItem(id);
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIResult getTbItems(Integer page,Integer pageSize){
        page = page==null?1:page;
        pageSize = pageSize==null?30:pageSize;
        return itemService.findTbItems(page,pageSize);
    }

    @RequestMapping("/item/save")
    @ResponseBody
    public TaotaoResult itemSave(TbItem tbItem, String desc){
        return itemService.addTbItem(tbItem,desc);
    }

    @RequestMapping("/rest/page/item-edit")
    public String itemEditWindow(){
        return "item-edit";
    }

    @RequestMapping("/rest/item/query/item/desc/{id}")
    @ResponseBody
    public TaotaoResult itemEdit(@PathVariable long id){
        TbItemDesc tbItemDesc = itemService.findTbItemDesc(id);
        return TaotaoResult.ok(tbItemDesc);
    }
}
