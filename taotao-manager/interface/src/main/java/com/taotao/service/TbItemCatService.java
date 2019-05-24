package com.taotao.service;

import com.taotao.result.EasyUICatTree;

import java.util.List;

public interface TbItemCatService {
    List<EasyUICatTree> getTbItemCatTree(long parentId);
}
