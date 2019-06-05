package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemParamService;
import com.taotao.vo.ItemParamShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ItemParamServiceImpl implements ItemParamService{
    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public EasyUIResult getItemParams(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ItemParamShow> itemParams = tbItemParamMapper.findItemParams();
        PageInfo<ItemParamShow> pageInfo = new PageInfo<>(itemParams);
        EasyUIResult result = new EasyUIResult(pageInfo.getTotal(),itemParams);
        return result;
    }

    @Override
    public TaotaoResult findItemParam(Long itemcatid) {
        TbItemParam tbItemParam = tbItemParamMapper.selectItemParam(itemcatid);
        return TaotaoResult.ok(tbItemParam);
    }

    @Override
    public TaotaoResult addItemParam(Long cid, String paramData) {
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        Date date = new Date();
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);
        tbItemParamMapper.insertParam(tbItemParam);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteParam(String ids) {
        String[] split = ids.split(",");
        List<Long> list = new ArrayList<>();
        for (String id:split) {
            list.add(Long.parseLong(id));
        }
        tbItemParamMapper.deleteParam(list);
        return TaotaoResult.ok();
    }

}
