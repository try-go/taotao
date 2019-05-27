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
    public EasyUIResult getTbItems(Integer page,Integer rows){
        page = page==null?1:page;
        Integer pageSize = rows==null?30:rows;
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

    @RequestMapping("/rest/item/update")
    @ResponseBody
    public TaotaoResult itemUpdate(TbItem tbItem,String desc){
        return itemService.updateTbitem(tbItem,desc);
    }
    //商品下架
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public TaotaoResult itemInstock(String ids){
        return itemService.updateTbitem(ids,2);
    }
    //商品上架
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public TaotaoResult itemReshelf(String ids){
        return itemService.updateTbitem(ids,1);
    }
    //商品删除
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public TaotaoResult itemDelete(String ids){
        return itemService.updateTbitem(ids,3);
    }
}
