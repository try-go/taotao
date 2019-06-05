package com.taotao.controller;

import com.content.service.SearchService;
import com.taotao.poi.PoiHandle;
import com.taotao.result.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

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
