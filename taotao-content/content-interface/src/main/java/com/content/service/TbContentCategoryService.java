package com.content.service;

import com.taotao.result.EasyUICatTree;

import java.util.List;

public interface TbContentCategoryService {
    List<EasyUICatTree> getContentCategorys(long parentId);
}
