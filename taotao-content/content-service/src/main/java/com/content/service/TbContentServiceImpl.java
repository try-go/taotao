package com.content.service;

import com.taotao.jedis.JedisClient;
import com.taotao.jedis.JedisClientCluster;
import com.taotao.jedis.JedisClientPool;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.result.Ad1;
import com.taotao.result.EasyUIResult;
import com.taotao.result.JsonUtils;
import com.taotao.result.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        JedisClient jedisClient = new JedisClientPool();
        String json = jedisClient.hget("TbContentServiceImpl" + "getContentOfAd1", categoryId.toString());
        if(json != null){
            System.out.println("redis");
            return JsonUtils.jsonToList(json,TbContent.class);
        }
        List<TbContent> tbContents = tbContentMapper.findTbContentByCategoryId(categoryId);
        jedisClient.hset("TbContentServiceImpl" + "getContentOfAd1",categoryId.toString(),JsonUtils.objectToJson(tbContents));
        System.out.println("datebase");
        return tbContents;
    }

    @Override
    public List<TbContent> getContentOfAd2(Long categoryId) {
        List<TbContent> tbContents = tbContentMapper.findTbContentByCategoryId(categoryId);
        return tbContents;
    }

    @Override
    public TaotaoResult deleteContent(String ids) {
        String[] split = ids.split(",");
        List<Long> list = new ArrayList<>();
        for (String id:split) {
            list.add(Long.parseLong(id));
        }
        tbContentMapper.deleteContent(list);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateContent(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        tbContentMapper.updateContent(tbContent);
        return TaotaoResult.ok();
    }
}
