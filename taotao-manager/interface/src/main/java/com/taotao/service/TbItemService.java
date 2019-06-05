package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;

public interface TbItemService {
    TbItemDesc findTbItemDesc(long id);
    TbItem findTbItem(long id);

    /**
     * 分页数据
     * @param pageNum 页码
     * @param pageSize 每页数据量
     * @return
     */
    EasyUIResult findTbItems(Integer pageNum, Integer pageSize);

    /**
     * 添加一个商品，要生产id,status,created,updated
     * 添加一个商品描述，要生产itemId,created,updated
     * @param tbItem
     */
    TaotaoResult addTbItem(TbItem tbItem, String desc,String itemParams);

    /**
     * 更新一个商品，要生产updated
     * 更新一个商品描述，要生产updated
     * @param tbItem
     * @param desc
     * @return
     */
    TaotaoResult updateTbitem(TbItem tbItem,String desc);
    TaotaoResult updateTbitem(String ids,int status);
}
