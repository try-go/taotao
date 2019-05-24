package com.taotao.mapper;

import com.taotao.pojo.TbItemDesc;

public interface TbItemDescMapper {

    void addTbItemDesc(TbItemDesc tbItemDesc);
    TbItemDesc findTbItemDesc(long id);
    void updateTbItemDesc(TbItemDesc tbItemDesc);
}