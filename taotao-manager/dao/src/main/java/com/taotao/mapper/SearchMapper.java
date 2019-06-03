package com.taotao.mapper;

import com.taotao.pojo.SearchItem;

import java.util.Date;
import java.util.List;

public interface SearchMapper {
    List<SearchItem> findSearchItems();
    List<SearchItem> findSearchItemByDate(Date date);
}
