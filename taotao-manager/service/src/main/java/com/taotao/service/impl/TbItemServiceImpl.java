package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.jedis.JedisClient;
import com.taotao.jedis.JedisClientPool;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.result.*;
import com.taotao.service.TbItemService;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TbItemServiceImpl implements TbItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public TbItemDesc findTbItemDesc(long id) {
        JedisClient jedisClient = new JedisClientPool();
        String redisKey = "TbItemServiceImpl-findTbItemDesc-"+id;
        String json = jedisClient.get(redisKey);
        if(json!=null){
            jedisClient.expire(redisKey,60*60*24);
            return JsonUtils.jsonToPojo(json,TbItemDesc.class);
        }
        TbItemDesc tbItemDesc = tbItemDescMapper.findTbItemDesc(id);
        jedisClient.set(redisKey, JsonUtils.objectToJson(tbItemDesc));
        jedisClient.expire(redisKey,60*60*24);
        return tbItemDesc;
    }

    @Override
    public TbItem findTbItem(long id) {
        //加入redis缓存
        JedisClient jedisClient = new JedisClientPool();
        String redisKey = "TbItemServiceImpl-findTbItem-"+id;
        String json = jedisClient.get(redisKey);
        if(json!=null){
            jedisClient.expire(redisKey,60*60*24);
            return JsonUtils.jsonToPojo(json,TbItem.class);
        }
        TbItem tbItem = tbItemMapper.findTbItem(id);
        jedisClient.set(redisKey, JsonUtils.objectToJson(tbItem));
        jedisClient.expire(redisKey,60*60*24);
        return tbItem;
    }

    @Override
    public EasyUIResult findTbItems(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TbItem> tbItems = tbItemMapper.findTbItems();
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItems);
        EasyUIResult result = new EasyUIResult(pageInfo.getTotal(),tbItems);
        return result;
    }

    @Override
    public TaotaoResult addTbItem(TbItem tbItem, String desc,String itemParams) {
        long id = IDUtils.genItemId();
        tbItem.setId(id);
        //商品状态，1-正常，2-下架，3-删除
        tbItem.setStatus((byte) 1);
        Date date = new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        tbItemMapper.addTbItem(tbItem);
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(id);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        tbItemDescMapper.addTbItemDesc(tbItemDesc);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(id);
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        tbItemParamItemMapper.insertItemParam(tbItemParamItem);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateTbitem(TbItem tbItem, String desc) {
        Date date = new Date();
        tbItem.setUpdated(date);
        tbItemMapper.updateTbItem(tbItem);
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemDesc(desc);
        tbItemDescMapper.updateTbItemDesc(tbItemDesc);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateTbitem(String ids,int status) {
        String[] split = ids.split(",");
        List<Long> list = new ArrayList<>();
        for (String s:split) {
            list.add(Long.parseLong(s));
        }
        TbItemStatusChange tbItemStatusChange = new TbItemStatusChange();
        tbItemStatusChange.setStatus(status);
        tbItemStatusChange.setIds(list);
        tbItemMapper.updateTbItemByIds(tbItemStatusChange);
        return TaotaoResult.ok();
    }
}
