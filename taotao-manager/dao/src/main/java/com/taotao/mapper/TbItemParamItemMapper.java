package com.taotao.mapper;


import com.taotao.pojo.TbItemParamItem;

public interface TbItemParamItemMapper {
   void insertItemParam(TbItemParamItem tbItemParamItem);
   TbItemParamItem selectItemParam(Long itemId);
}