package com.taotao.mapper;

import com.taotao.pojo.TbItem;

import java.util.List;

public interface TbItemMapper {
	TbItem findTbItem(long id);
	List<TbItem> findTbItems();
	void addTbItem(TbItem tbItem);
}