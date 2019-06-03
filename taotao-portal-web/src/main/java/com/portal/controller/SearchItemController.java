package com.portal.controller;

import com.content.service.SearchService;
import com.taotao.result.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
public class SearchItemController {
    @Autowired
    private SearchService searchService;

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
}
