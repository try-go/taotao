package com.taotao.mapper;


import com.taotao.pojo.TbItemParam;
import com.taotao.vo.ItemParamShow;

import java.util.List;

public interface TbItemParamMapper {
    List<ItemParamShow> findItemParams();
    TbItemParam selectItemParam(Long itemcatid);
    void insertParam(TbItemParam tbItemParam);
    void deleteParam(List<Long> ids);
}