package com.taotao.service;

import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;

public interface ItemParamService {
    EasyUIResult getItemParams(Integer pageNum, Integer pageSize);
    TaotaoResult findItemParam(Long itemcatid);
    TaotaoResult addItemParam(Long cid,String paramData);
    TaotaoResult deleteParam(String ids);
}
