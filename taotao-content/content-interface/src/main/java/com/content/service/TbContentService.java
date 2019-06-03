package com.content.service;

import com.taotao.pojo.TbContent;
import com.taotao.result.Ad1;
import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;

import java.util.List;

public interface TbContentService {
    EasyUIResult getContentByCategoryId(Long categoryId);

    /**
     * 添加一个内容，要生产created，updated
     * @param tbContent
     * @return
     */
    TaotaoResult addTbContent(TbContent tbContent);

    List<TbContent> getContentOfAd1(Long categoryId);
    List<TbContent> getContentOfAd2(Long categoryId);

    TaotaoResult deleteContent(String ids);

    TaotaoResult updateContent(TbContent tbContent);
}
