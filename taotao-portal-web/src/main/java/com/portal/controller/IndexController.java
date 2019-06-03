package com.portal.controller;

import com.content.service.TbContentService;
import com.taotao.pojo.TbContent;
import com.taotao.result.Ad1;
import com.taotao.result.Ad2;
import com.taotao.result.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Value("${HEIGHT}")
    private Integer height;
    @Value("${WIDTH}")
    private Integer width;
    @Value("${WIDTHB}")
    private Integer widthB;
    @Value("${HEIGHTB}")
    private Integer heightB;

    @Autowired
    private TbContentService tbContentService;

    @RequestMapping("/index")
    public String index(Model model){
        //大广告位
        List<TbContent> ad1Contents = tbContentService.getContentOfAd1(Long.valueOf(89));
        List<Ad1> ad1 = new ArrayList<>();
        for (TbContent tbContent:ad1Contents) {
            Ad1 one = new Ad1();
            one.setSrcB(tbContent.getPic2());
            one.setSrc(tbContent.getPic());
            one.setAlt(tbContent.getTitle());
            one.setHref(tbContent.getUrl());
            one.setHeight(height);
            one.setHeightB(heightB);
            one.setWidth(width);
            one.setWidthB(widthB);
            ad1.add(one);
        }
        model.addAttribute("ad1", JsonUtils.objectToJson(ad1));
        //中广告位
        List<TbContent> Ad2Contents = tbContentService.getContentOfAd1(Long.valueOf(96));
        List<Ad2> ad2 = new ArrayList<>();
        int index = 0;
        for (TbContent tbContent:Ad2Contents) {
            Ad2 one = new Ad2();
            one.setAlt(tbContent.getTitle());
            one.setHref(tbContent.getUrl());
            one.setIndex(index);
            one.setSrc(tbContent.getPic());
            one.setExt("");
            ad2.add(one);
            index++;
        }
        model.addAttribute("ad2", JsonUtils.objectToJson(ad2));
        return "index";
    }
}
