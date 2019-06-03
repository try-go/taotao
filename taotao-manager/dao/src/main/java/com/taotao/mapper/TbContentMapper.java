package com.taotao.mapper;


import com.taotao.pojo.TbContent;

import java.util.List;

public interface TbContentMapper {
    List<TbContent> findTbContentByCategoryId(Long categoryId);
    void addTbContent(TbContent tbContent);
    void deleteContent(List<Long> ids);
    void updateContent(TbContent tbContent);
}