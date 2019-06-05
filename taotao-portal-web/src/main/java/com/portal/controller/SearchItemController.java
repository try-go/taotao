package com.portal.controller;

import com.content.service.SearchService;
import com.portal.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.result.SearchResult;
import com.taotao.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
public class SearchItemController {
    @Autowired
    private SearchService searchService;
    @Autowired
    private TbItemService tbItemService;

    @RequestMapping("/search.html")
    public String searchItem(String q, @RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "60") int rows, Model model){
        String queryString = null;
        try {
            queryString = new String(q.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        SearchResult result = searchService.getSearchItem(queryString, page, rows);
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", result.getPageCount());
        model.addAttribute("itemList", result.getItemList());
        model.addAttribute("page", page);
        return "search";
    }

    @RequestMapping("/item/{itemId}.html")
    public String showItem(@PathVariable Long itemId,Model model){
        TbItem tbItem = tbItemService.findTbItem(itemId);
        Item item = new Item(tbItem);
        model.addAttribute("item",item);
        return "item";
    }

    @RequestMapping("/item/desc/{itemId}.html")
    @ResponseBody
    public String getItemDesc(@PathVariable Long itemId){
        TbItemDesc tbItemDesc = tbItemService.findTbItemDesc(itemId);
        return tbItemDesc.getItemDesc();
    }

    @RequestMapping("/item/param/{itemId}.html")
    @ResponseBody
    public String queryItemParam(@PathVariable Long itemId){
        return searchService.getItemParam(itemId);
    }
}
