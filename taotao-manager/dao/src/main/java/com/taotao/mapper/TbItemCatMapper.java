package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItemCat;

public interface TbItemCatMapper {
    /**
     * 根据父级id查询所有的子类分类
     * @param parentId
     * @return
     */
    List<TbItemCat> getTbItemCatByParentId(long parentId);
}