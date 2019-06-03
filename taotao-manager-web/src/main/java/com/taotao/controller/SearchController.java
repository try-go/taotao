package com.taotao.controller;

import com.content.service.SearchService;
import com.taotao.result.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/index/importall")
    @ResponseBody
    public TaotaoResult updateSearchItem(){
        return searchService.updateIndex();
    }
}
