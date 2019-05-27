package com.taotao.mapper;


import com.taotao.pojo.TbContentCategory;

import java.util.List;

public interface TbContentCategoryMapper {

    List<TbContentCategory> getContentCategorys(long parentId);
    void addContentCategory(TbContentCategory tbContentCategory);
    TbContentCategory findContentCategoryById(Long id);
    void updateContentCategoryIsParent(TbContentCategory tbContentCategory);
    void updateContentCategoryName(TbContentCategory tbContentCategory);
    void updateContentCategoryStatus(TbContentCategory tbContentCategory);
}