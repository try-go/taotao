package com.content.service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.result.Ad1;
import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class TbContentServiceImpl implements TbContentService {
    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public EasyUIResult getContentByCategoryId(Long categoryId) {
        List<TbContent> tbContents = tbContentMapper.findTbContentByCategoryId(categoryId);
        EasyUIResult result = new EasyUIResult(tbContents.size(),tbContents);
        return result;
    }

    @Override
    public TaotaoResult addTbContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        tbContentMapper.addTbContent(tbContent);
        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentOfAd1(Long categoryId) {
        List<TbContent> tbContents = tbContentMapper.findTbContentByCategoryId(categoryId);
        return tbContents;
    }
}
