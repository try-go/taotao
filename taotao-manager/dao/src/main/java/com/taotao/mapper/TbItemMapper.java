package com.taotao.mapper;

import com.taotao.pojo.TbItem;
import com.taotao.result.TbItemStatusChange;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemMapper {
	TbItem findTbItem(long id);
	List<TbItem> findTbItems();
	void addTbItem(TbItem tbItem);
	void updateTbItem(TbItem tbItem);
	void updateTbItemByIds(TbItemStatusChange tbItemStatusChange);
}