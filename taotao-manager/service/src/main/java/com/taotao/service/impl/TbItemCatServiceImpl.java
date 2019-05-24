package com.taotao.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.result.EasyUICatTree;
import com.taotao.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public List<EasyUICatTree> getTbItemCatTree(long parentId) {
        List<TbItemCat> tbItemCats = tbItemCatMapper.getTbItemCatByParentId(parentId);
        List<EasyUICatTree> list = new ArrayList<>();
        for (TbItemCat tbItemCat :tbItemCats){
            EasyUICatTree easyUICatTree = new EasyUICatTree();
            easyUICatTree.setId(tbItemCat.getId());
            easyUICatTree.setText(tbItemCat.getName());
            easyUICatTree.setState(tbItemCat.getIsParent()?"closed":"open");
            list.add(easyUICatTree);
        }
        return list;
    }
}
